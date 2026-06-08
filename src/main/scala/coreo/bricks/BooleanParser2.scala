package coreo.bricks


/**
 * Awesome—here’s a clean, Scala 3 version that parses Boolean expressions from a string with variables and supports AND, OR, NOT, XOR, NAND, NOR, NXOR (aka XNOR). It includes:
 *
 * An AST (enum BoolExpr)
 * Evaluation for a given environment (Map[String, Boolean])
 * Boolean derivative (Boolean difference):
 * ∂f/∂x=f[x:=0]⊕f[x:=1]\displaystyle \partial f/\partial x = f[x:=0] \oplus f[x:=1]∂f/∂x=f[x:=0]⊕f[x:=1]
 * A simplifier and pretty-printer
 * A lexer + recursive-descent parser with proper precedence:
 *
 * NOT (highest)
 * AND, NAND
 * XOR, NXOR
 * OR, NOR (lowest)
 *
 *
 * Case-insensitive keywords; variable names preserved
 *
 * You can paste this into a single file and run with scala-cli or your IDE.
 */

//> using scala "3.3.1"

// ========================================
// Boolean Expression (AST)
// ========================================
enum BoolExpr:
  case True
  case False
  case Var(name: String)
  case Not(e: BoolExpr)
  case And(l: BoolExpr, r: BoolExpr)
  case Or(l: BoolExpr, r: BoolExpr)
  case Xor(l: BoolExpr, r: BoolExpr)
  case Nand(l: BoolExpr, r: BoolExpr)
  case Nor(l: BoolExpr, r: BoolExpr)
  case Nxor(l: BoolExpr, r: BoolExpr) // aka XNOR

import BoolExpr.*

// ========================================
// Evaluation
// ========================================
object BoolEval:
  type Env = Map[String, Boolean]

  def eval(e: BoolExpr, env: Env): Boolean = e match
    case True => true
    case False => false
    case Var(n) => env.getOrElse(n, sys.error(s"Missing value for variable: $n"))
    case Not(a) => !eval(a, env)
    case And(a, b) => eval(a, env) && eval(b, env)
    case Or(a, b) => eval(a, env) || eval(b, env)
    case Xor(a, b) => eval(a, env) ^ eval(b, env)
    case Nand(a, b) => !(eval(a, env) && eval(b, env))
    case Nor(a, b) => !(eval(a, env) || eval(b, env))
    case Nxor(a, b) => !(eval(a, env) ^ eval(b, env))

// ========================================
// Substitution (cofactor helper)
// ========================================
object BoolSubst:
  def substitute(e: BoolExpr, v: String, value: Boolean): BoolExpr = e match
    case True | False => e
    case Var(n) => if n == v then (if value then True else False) else e
    case Not(a) => Not(substitute(a, v, value))
    case And(a, b) => And(substitute(a, v, value), substitute(b, v, value))
    case Or(a, b) => Or(substitute(a, v, value), substitute(b, v, value))
    case Xor(a, b) => Xor(substitute(a, v, value), substitute(b, v, value))
    case Nand(a, b) => Nand(substitute(a, v, value), substitute(b, v, value))
    case Nor(a, b) => Nor(substitute(a, v, value), substitute(b, v, value))
    case Nxor(a, b) => Nxor(substitute(a, v, value), substitute(b, v, value))

// ========================================
// Simplifier (basic but effective)
// ========================================
object BoolSimplify:
  def simplify(e: BoolExpr): BoolExpr = e match
    // NOT
    case Not(True) => False
    case Not(False) => True
    case Not(Not(a)) => simplify(a)

    // AND
    case And(False, _) => False
    case And(_, False) => False
    case And(True, a) => simplify(a)
    case And(a, True) => simplify(a)
    case And(a, b) =>
      val sa = simplify(a);
      val sb = simplify(b)
      if sa == False || sb == False then False
      else if sa == True then sb
      else if sb == True then sa
      else if sa == sb then sa
      else And(sa, sb)

    // OR
    case Or(True, _) => True
    case Or(_, True) => True
    case Or(False, a) => simplify(a)
    case Or(a, False) => simplify(a)
    case Or(a, b) =>
      val sa = simplify(a);
      val sb = simplify(b)
      if sa == True || sb == True then True
      else if sa == False then sb
      else if sb == False then sa
      else if sa == sb then sa
      else Or(sa, sb)

    // XOR
    case Xor(False, a) => simplify(a)
    case Xor(a, False) => simplify(a)
    case Xor(True, a) => simplify(Not(a))
    case Xor(a, True) => simplify(Not(a))
    case Xor(a, b) =>
      val sa = simplify(a);
      val sb = simplify(b)
      if sa == sb then False else Xor(sa, sb)

    // NAND
    case Nand(False, _) => True // !(0 & _) = 1
    case Nand(_, False) => True
    case Nand(True, a) => simplify(Not(a))
    case Nand(a, True) => simplify(Not(a))
    case Nand(a, b) =>
      val sa = simplify(a);
      val sb = simplify(b)
      if sa == False || sb == False then True
      else if sa == True then simplify(Not(sb))
      else if sb == True then simplify(Not(sa))
      else if sa == sb then simplify(Not(sa))
      else Nand(sa, sb)

    // NOR
    case Nor(True, _) => False // !(1 | _) = 0
    case Nor(_, True) => False
    case Nor(False, a) => simplify(Not(a))
    case Nor(a, False) => simplify(Not(a))
    case Nor(a, b) =>
      val sa = simplify(a);
      val sb = simplify(b)
      if sa == True || sb == True then False
      else if sa == False then simplify(Not(sb))
      else if sb == False then simplify(Not(sa))
      else if sa == sb then simplify(Not(sa))
      else Nor(sa, sb)

    // NXOR (XNOR)
    case Nxor(False, a) => simplify(Not(a)) // 0 ⊙ a = !a
    case Nxor(a, False) => simplify(Not(a))
    case Nxor(True, a) => simplify(a) // 1 ⊙ a = a
    case Nxor(a, True) => simplify(a)
    case Nxor(a, b) =>
      val sa = simplify(a);
      val sb = simplify(b)
      if sa == sb then True else Nxor(sa, sb)

    case x => x

// ========================================
// Boolean Derivative (Boolean difference)
// ========================================
object BoolDerivative:

  import BoolSubst.*
  import BoolSimplify.*
  import BoolEval.*

  /** ∂e/∂x = e[x:=0] XOR e[x:=1] */
  def derivative(e: BoolExpr, x: String): BoolExpr =
    val f0 = substitute(e, x, value = false)
    val f1 = substitute(e, x, value = true)
    simplify(Xor(f0, f1))

  /** Value of derivative at a point env */
  def derivativeAt(e: BoolExpr, x: String, env: Env): Boolean =
    val env0 = env + (x -> false)
    val env1 = env + (x -> true)
    eval(e, env0) ^ eval(e, env1)

// ========================================
// Pretty Printer
// ========================================
object BoolShow:
  // precedence: NOT=4, AND/NAND=3, XOR/NXOR=2, OR/NOR=1, atoms=5
  private def prec(e: BoolExpr): Int = e match
    case Not(_) => 4
    case And(_, _) | Nand(_, _) => 3
    case Xor(_, _) | Nxor(_, _) => 2
    case Or(_, _) | Nor(_, _) => 1
    case _ => 5

  private def showBin(e: BoolExpr, parentPrec: Int): String =
    val s = show(e)
    if prec(e) < parentPrec then s"($s)" else s

  def show(e: BoolExpr): String = e match
    case True => "TRUE"
    case False => "FALSE"
    case Var(n) => n
    case Not(a) =>
      a match
        case Var(_) | True | False => s"NOT ${show(a)}"
        case _ => s"NOT (${show(a)})"
    case And(a, b) => s"${showBin(a, 3)} AND ${showBin(b, 3)}"
    case Nand(a, b) => s"${showBin(a, 3)} NAND ${showBin(b, 3)}"
    case Xor(a, b) => s"${showBin(a, 2)} XOR ${showBin(b, 2)}"
    case Nxor(a, b) => s"${showBin(a, 2)} NXOR ${showBin(b, 2)}" // aka XNOR
    case Or(a, b) => s"${showBin(a, 1)} OR ${showBin(b, 1)}"
    case Nor(a, b) => s"${showBin(a, 1)} NOR ${showBin(b, 1)}"

// ========================================
// Lexer & Parser
// ========================================
object BoolParser:
  enum Tok:
    case TAnd, TOr, TNot, TXor, TNand, TNor, TNxor
    case TLParen, TRParen
    case TTrue, TFalse
    case TVar(name: String)
    case TEOF

  import Tok.*

  private val identStart: Char => Boolean = c => c.isLetter || c == '_'
  private val identPart: Char => Boolean = c => c.isLetterOrDigit || c == '_'

  def tokenize(input: String): List[Tok] =
    val s = input
    val n = s.length
    val out = scala.collection.mutable.ListBuffer.empty[Tok]
    var i = 0

    def inBounds = i < n

    def peek: Char = if inBounds then s.charAt(i) else 0.toChar

    def next(): Char = {
      val c = peek; i += 1; c
    }

    def skipWs(): Unit = while inBounds && s.charAt(i).isWhitespace do i += 1

    while inBounds do
      skipWs()
      if !inBounds then ()
      else
        peek match
          case '(' => out += TLParen; i += 1
          case ')' => out += TRParen; i += 1

          // ---- Optional symbol operators (uncomment to enable) ----
          // case '!' => out += TNot; i += 1
          // case '&' if i + 1 < n && s.charAt(i + 1) == '&' => out += TAnd; i += 2
          // case '|' if i + 1 < n && s.charAt(i + 1) == '|' => out += TOr;  i += 2
          // case '^' => out += TXor; i += 1
          // case '=' if i + 1 < n && s.charAt(i + 1) == '=' => out += TNxor; i += 2  // NXOR as '=='
          // case '~' if i + 1 < n && s.charAt(i + 1) == '&' => out += TNand; i += 2  // ~&
          // case '~' if i + 1 < n && s.charAt(i + 1) == '|' => out += TNor;  i += 2  // ~|
          // case '^' if i + 1 < n && s.charAt(i + 1) == '~' => out += TNxor; i += 2  // ^~
          // case '~' if i + 1 < n && s.charAt(i + 1) == '^' => out += TNxor; i += 2  // ~^
          // ----------------------------------------------------------

          case c if identStart(c) =>
            val sb = new StringBuilder
            sb.append(next())
            while inBounds && identPart(peek) do sb.append(next())
            val word = sb.toString
            val upper = word.toUpperCase
            upper match
              case "AND" => out += TAnd
              case "OR" => out += TOr
              case "NOT" => out += TNot
              case "XOR" => out += TXor
              case "NAND" => out += TNand
              case "NOR" => out += TNor
              case "NXOR" => out += TNxor
              case "XNOR" => out += TNxor // synonym
              case "TRUE" => out += TTrue
              case "FALSE" => out += TFalse
              case _ => out += TVar(word) // preserve original variable name
          case other =>
            sys.error(s"Unexpected character '$other' at position $i")

    out += TEOF
    out.toList

  final class Parser(tokens: List[Tok]):
    private var i = 0

    private def cur: Tok = if i < tokens.length then tokens(i) else TEOF

    private def is(t: Tok): Boolean = cur == t

    private def eat(t: Tok): Unit =
      if cur == t then i += 1 else sys.error(s"Expected $t but found $cur at token index $i")

    def parseExpr(): BoolExpr = parseOrNor()

    // Level 1 (lowest): OR / NOR
    private def parseOrNor(): BoolExpr =
      var left = parseXorNxor()
      var loop = true
      while loop do
        cur match
          case TOr => eat(TOr);
            val r = parseXorNxor(); left = Or(left, r)
          case TNor => eat(TNor);
            val r = parseXorNxor(); left = Nor(left, r)
          case _ => loop = false
      left

    // Level 2: XOR / NXOR
    private def parseXorNxor(): BoolExpr =
      var left = parseAndNand()
      var loop = true
      while loop do
        cur match
          case TXor => eat(TXor);
            val r = parseAndNand(); left = Xor(left, r)
          case TNxor => eat(TNxor);
            val r = parseAndNand(); left = Nxor(left, r)
          case _ => loop = false
      left

    // Level 3: AND / NAND
    private def parseAndNand(): BoolExpr =
      var left = parseNot()
      var loop = true
      while loop do
        cur match
          case TAnd => eat(TAnd);
            val r = parseNot(); left = And(left, r)
          case TNand => eat(TNand);
            val r = parseNot(); left = Nand(left, r)
          case _ => loop = false
      left

    // Level 4: NOT (prefix, highest)
    private def parseNot(): BoolExpr = cur match
      case TNot => eat(TNot); Not(parseNot())
      case _ => parsePrimary()

    // Primary: VAR | TRUE | FALSE | '(' expr ')'
    private def parsePrimary(): BoolExpr = cur match
      case TLParen => eat(TLParen);
        val e = parseExpr(); eat(TRParen); e
      case TTrue => eat(TTrue); True
      case TFalse => eat(TFalse); False
      case TVar(n) => eat(TVar(n)); Var(n)
      case other => sys.error(s"Unexpected token $other at index $i")

  def parse(input: String): BoolExpr =
    val toks = tokenize(input)
    val p = Parser(toks)
    p.parseExpr()

// ========================================
// Nice Scala 3 extensions
// ========================================
object syntax:

  import BoolEval.*

  export BoolEval.Env

  extension (e: BoolExpr)
    def eval(env: Env): Boolean = BoolEval.eval(e, env)
    def d(x: String): BoolExpr = BoolDerivative.derivative(e, x)
    def dAt(x: String, env: Env): Boolean = BoolDerivative.derivativeAt(e, x, env)
    def show: String = BoolShow.show(BoolSimplify.simplify(e))

// ========================================
// Demo
// ========================================
@main def demo(): Unit =
  import BoolParser.*
  import syntax.*

  val input = "(x AND y) OR (NOT x AND z) XOR (a NXOR b) NOR w"
  val f = parse(input)
  println(s"Parsed : ${f.show}")

  val env = Map("x" -> true, "y" -> true, "z" -> false, "a" -> true, "b" -> true, "w" -> false)
  println(s"f(env): ${f.eval(env)}")

  val dfx = f.d("x")
  println(s"df/dx : ${dfx.show}")
  println(s"(df/dx)(env): ${dfx.eval(env)}")

  // Quick gate checks
  def chk(s: String): Unit =
    val e = parse(s)
    println(f"$s%-30s ==> ${e.show}")

  chk("x NAND y")
  chk("x NOR y")
  chk("x XOR y")
  chk("x NXOR y")
  chk("(TRUE NAND x) AND (FALSE NOR y) OR (x NXOR x)")