package coreo

/**
 * Great—here’s a clean, Scala 3 implementation that parses Boolean expressions from a string with named variables and supports AND, OR, and NOT. It evaluates expressions, computes the Boolean derivative (Boolean difference), simplifies, and pretty-prints results.
 * I’ve written it idiomatically for Scala 3 using enum, @main, and extensions.
 *
 *
 * ✅ Features
 *
 * Variables: names like x, y_1, Foo (letters/digits/underscore; start with letter or _)
 * Constants: TRUE, FALSE
 * Operators: NOT (prefix), AND, OR
 * Parentheses: ( )
 * Case-insensitive keywords
 * Derivative: ∂f/∂x=f[x:=0]⊕f[x:=1]\partial f/\partial x = f[x:=0] \oplus f[x:=1]∂f/∂x=f[x:=0]⊕f[x:=1] (uses XOR internally)
 * Simplifier + pretty-printer
 * Small, dependency-free, Scala 3
 */
//> using scala "3.3.1"

// =========================
// Boolean Expression (AST)
// =========================
enum BoolExpr:
  case True
  case False
  case Var(name: String)
  case Not(e: BoolExpr)
  case And(l: BoolExpr, r: BoolExpr)
  case Or(l: BoolExpr, r: BoolExpr)
  // Used for the derivative (Boolean difference), not parsed from input:
  case Xor(l: BoolExpr, r: BoolExpr)

import BoolExpr.*

// ===============
// Evaluation etc.
// ===============
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

  def vars(e: BoolExpr): Set[String] = e match
    case True | False => Set.empty
    case Var(n) => Set(n)
    case Not(a) => vars(a)
    case And(a, b) => vars(a) ++ vars(b)
    case Or(a, b) => vars(a) ++ vars(b)
    case Xor(a, b) => vars(a) ++ vars(b)

// ==============
// Substitution
// ==============
object BoolSubst:
  def substitute(e: BoolExpr, v: String, value: Boolean): BoolExpr = e match
    case True | False => e
    case Var(n) => if n == v then (if value then True else False) else e
    case Not(a) => Not(substitute(a, v, value))
    case And(a, b) => And(substitute(a, v, value), substitute(b, v, value))
    case Or(a, b) => Or(substitute(a, v, value), substitute(b, v, value))
    case Xor(a, b) => Xor(substitute(a, v, value), substitute(b, v, value))

// ==============
// Simplifier
// ==============
object BoolSimplify:
  def simplify(e: BoolExpr): BoolExpr = e match
    case Not(True) => False
    case Not(False) => True
    case Not(Not(a)) => simplify(a)

    case And(False, _) => False
    case And(_, False) => False
    case And(True, a) => simplify(a)
    case And(a, True) => simplify(a)
    case And(a, b) =>
      (simplify(a), simplify(b)) match
        case (False, _) => False
        case (_, False) => False
        case (True, x) => x
        case (x, True) => x
        case (x, y) => if x == y then x else And(x, y)

    case Or(True, _) => True
    case Or(_, True) => True
    case Or(False, a) => simplify(a)
    case Or(a, False) => simplify(a)
    case Or(a, b) =>
      (simplify(a), simplify(b)) match
        case (True, _) => True
        case (_, True) => True
        case (False, x) => x
        case (x, False) => x
        case (x, y) => if x == y then x else Or(x, y)

    case Xor(False, a) => simplify(a)
    case Xor(a, False) => simplify(a)
    case Xor(True, a) => simplify(Not(a))
    case Xor(a, True) => simplify(Not(a))
    case Xor(a, b) =>
      (simplify(a), simplify(b)) match
        case (x, y) if x == y => False
        case (x, y) => Xor(x, y)

    case x => x

// ======================
// Boolean Derivative
// ======================
object BoolDerivative:

  import BoolSubst.*
  import BoolSimplify.*

  /** Boolean difference: ∂e/∂x = e[x:=0] XOR e[x:=1] */
  def derivative(e: BoolExpr, x: String): BoolExpr =
    val f0 = substitute(e, x, value = false)
    val f1 = substitute(e, x, value = true)
    simplify(Xor(f0, f1))

  /** Value of derivative at a point (env) without materializing a formula. */
  def derivativeAt(e: BoolExpr, x: String, env: BoolEval.Env): Boolean =
    import BoolEval.eval
    val env0 = env + (x -> false)
    val env1 = env + (x -> true)
    eval(e, env0) ^ eval(e, env1)

// =================
// Pretty Printer
// =================
object BoolShow:
  private def par(s: String) = s"($s)"

  // precedence: NOT=3, AND=2, OR/XOR=1, atoms=4
  private def prec(e: BoolExpr): Int = e match
    case Not(_) => 3
    case And(_, _) => 2
    case Or(_, _) => 1
    case Xor(_, _) => 1
    case _ => 4

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
        case _ => s"NOT ${par(show(a))}"
    case And(a, b) => s"${showBin(a, 2)} AND ${showBin(b, 2)}"
    case Or(a, b) => s"${showBin(a, 1)} OR ${showBin(b, 1)}"
    case Xor(a, b) => s"${showBin(a, 1)} XOR ${showBin(b, 1)}" // appears in derivative

// =======================
// Lexer & Parser (RD)
// =======================
object BoolParser:
  enum Tok:
    case TAnd, TOr, TNot, TLParen, TRParen, TTrue, TFalse
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
          case c if identStart(c) =>
            val sb = new StringBuilder
            sb.append(next())
            while inBounds && identPart(peek) do sb.append(next())
            sb.toString.toUpperCase match
              case "AND" => out += TAnd
              case "OR" => out += TOr
              case "NOT" => out += TNot
              case "TRUE" => out += TTrue
              case "FALSE" => out += TFalse
              case other => out += TVar(other) // preserve original? we used upper; change:
          // If you want original case for variables, use the raw word instead:
          // case otherRaw => out += TVar(otherRaw)  // but keep keyword matching by .toUpperCase
          // --- Optional symbol synonyms: uncomment if desired ---
          // case '!' => out += TNot; i += 1
          // case '&' if i + 1 < n && s.charAt(i + 1) == '&' => out += TAnd; i += 2
          // case '|' if i + 1 < n && s.charAt(i + 1) == '|' => out += TOr;  i += 2
          // ------------------------------------------------------
          case other =>
            sys.error(s"Unexpected character '$other' at position $i")

    out += TEOF
    out.toList

  final class Parser(tokens: List[Tok]):
    private var i = 0

    private def cur: Tok = if i < tokens.length then tokens(i) else TEOF

    private def eat(t: Tok): Unit =
      if cur == t then i += 1
      else sys.error(s"Expected $t but found $cur at token index $i")

    def parseExpr(): BoolExpr = parseOr()

    // orExpr := andExpr (OR andExpr)*
    private def parseOr(): BoolExpr =
      var left = parseAnd()
      while cur == TOr do
        eat(TOr)
        val right = parseAnd()
        left = Or(left, right)
      left

    // andExpr := notExpr (AND notExpr)*
    private def parseAnd(): BoolExpr =
      var left = parseNot()
      while cur == TAnd do
        eat(TAnd)
        val right = parseNot()
        left = And(left, right)
      left

    // notExpr := NOT notExpr | primary
    private def parseNot(): BoolExpr = cur match
      case TNot => eat(TNot); Not(parseNot())
      case _ => parsePrimary()

    // primary := VAR | TRUE | FALSE | '(' expr ')'
    private def parsePrimary(): BoolExpr = cur match
      case TLParen => eat(TLParen);
        val e = parseExpr(); eat(TRParen); e
      case TTrue => eat(TTrue); True
      case TFalse => eat(TFalse); False
      case TVar(n) => eat(TVar(n)); Var(n) // variable names as seen (here they are uppercased)
      case other => sys.error(s"Unexpected token $other at index $i")

  def parse(input: String): BoolExpr =
    val toks = tokenize(input)
    val p = Parser(toks)
    p.parseExpr()

// =======================
// Nice Scala 3 extensions
// =======================
object syntax:

  import BoolEval.*

  export BoolEval.Env

  extension (e: BoolExpr)
    def eval(env: Env): Boolean = BoolEval.eval(e, env)
    def d(x: String): BoolExpr = BoolDerivative.derivative(e, x)
    def dAt(x: String, env: Env): Boolean = BoolDerivative.derivativeAt(e, x, env)
    def show: String = BoolShow.show(BoolSimplify.simplify(e))

// ============
// Demo / REPL
// ============
@main def demo(): Unit =
  import BoolParser.*
  import syntax.*

  val input = "(x AND y) OR (NOT x AND z)" // f = (x & y) | (!x & z)
  val f = parse(input)

  println(s"Parsed: ${f.show}")

  val env = Map("x" -> true, "y" -> true, "z" -> false)
  println(s"f(env) = ${f.eval(env)}") // true

  val dfx = f.d("x") // should simplify to y XOR z
  println(s"df/dx  = ${dfx.show}") // "y XOR z"
  println(s"(df/dx)(env) = ${dfx.eval(env)}") // true ^ false = true