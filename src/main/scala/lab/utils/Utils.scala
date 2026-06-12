package lab.utils

/**
 * Extracts all public or declared fields of an object `a`
 * whose type is a subtype of the given `target` class.
 *
 * This function uses Java reflection to inspect the runtime class of `a`,
 * collecting every field whose type is assignable to `target`.
 *
 * @param a
 *   The instance whose members should be inspected.
 *
 * @param target
 *   A `Class[T]` representing the desired supertype. Any field whose type
 *   satisfies `target.isAssignableFrom(fieldType)` will be included.
 *
 * @tparam A
 *   The static type of the input value `a`. This type parameter is not used
 *   for reflection; the runtime class of `a` is inspected instead.
 *
 * @tparam T
 *   The target supertype used to filter members. The resulting list contains
 *   values of this type.
 *
 * @return
 *   A list of `(String, T)` pairs, where each pair contains:
 *     - the field name
 *     - the field value cast to `T`
 *
 *   Only fields are returned.
 *
 * @note
 *   - Both public and declared fields are inspected.
 *   - Private/protected fields are made accessible via `setAccessible(true)`.
 *   - Reflection may throw exceptions if access fails or invocation errors occur.
 *   - Method extraction can be enabled by uncommenting the final concatenation.
 *
 * @example
 *   case class Example(x: Int, y: String, z: Option[Int])
 *   val e = Example(1, "hello", Some(3))
 *
 *   // Extract all members that are subtypes of Option[_]
 *   membersOfSubtype(e, classOf[Option[?]])
 *   // res: List(("z", Some(3)))
 */
def collectMembersOfType[A, T](a: A, target: Class[T]): List[(String, T)] =
  val cls = a.getClass

  val fields =
    cls.getFields.toList ++ cls.getDeclaredFields.toList
  val methods =
    cls.getMethods.toList ++ cls.getDeclaredMethods.toList

  val fieldMatches =
    fields.collect {
      case f if target.isAssignableFrom(f.getType) =>
        f.setAccessible(true)
        f.getName -> f.get(a).asInstanceOf[T]
    }

  fieldMatches


object usage:
  class A {
    val s: String = "hello"
    private val hidden: String = "secret"

    def number: Int = 42
  }

  class B extends A {
    def extra: String = "more"
  }

  val b = B()

  val strings = collectMembersOfType(b, classOf[String])

  @main
  def test() = {
    println(strings)
  }

def fieldsOfExactType[A, T](a: A, target: Class[T]): List[(String, T)] =
  val cls = a.getClass

  // includes inherited public fields + declared fields
  val fields =
    cls.getFields.toList ++ cls.getDeclaredFields.toList

  fields.collect {
    case f if f.getType == target =>
      f.setAccessible(true)
      f.getName -> f.get(a).asInstanceOf[T]
  }

object fieldOfExactType:

  class Base:
    val base: String = "base"

  class Child extends Base:
    val child: String = "child-val"
    val number: Int = 42

  val c = Child()

  val strings = fieldsOfExactType(c, classOf[String])

  @main
  def usage2() = println(strings)

extension [A](a: A)
  def use(f: A => Unit): A = {
    f(a);
    a
  }

import java.util.regex.Pattern

object IdentifierSplit:

  private val SplitPattern: Pattern =
    Pattern.compile(
      """(?<=\p{Ll})(?=\p{Lu})|       # camelCase boundary
         (?<=\p{L})(?=\p{Nd})|       # letter -> digit
         (?<=\p{Nd})(?=\p{L})|       # digit -> letter
         (?<=\p{Lu})(?=\p{Lu}\p{Ll})|# acronym boundary
         [_\-\s]+                    # snake_case / kebab-case / spaces
      """.replaceAll("\\s+", "")
    )

  def split(input: String): Array[String] =
    SplitPattern.split(input).filter(_.nonEmpty)

  @main def runSplit(): Unit =
    val examples = Seq(
      "snake_case",
      "kebab-case",
      "CamelCase",
      "XMLHttpRequest",
      "user_id",
      "HTTP_server_response",
      "mixed_SnakeCamelCase123Test",
      "Übergang_test42ABCd"
    )

    examples.foreach { s =>
      println(s"$s -> ${IdentifierSplit.split(s).mkString(" | ")}")
    }


object IdentifierCheck:


  private val PatternSplit: Pattern =
    Pattern.compile(
      """(?<=\p{Ll})(?=\p{Lu})|
         (?<=\p{L})(?=\p{Nd})|
         (?<=\p{Nd})(?=\p{L})|
         (?<=\p{Lu})(?=\p{Lu}\p{Ll})|
         [_\-\s]+""".replaceAll("\\s+", "")
    )

  def containsSplitPoints(input: String): Boolean =
    PatternSplit.matcher(input).find()

  private val ValidPattern: Pattern =
    Pattern.compile(
      """^\p{L}[\p{L}\p{Nd}]*
         ([_\-\s]?\p{L}[\p{L}\p{Nd}]*)*$""".replaceAll("\\s+", "")
    )

  def isValidIdentifier(input: String): Boolean =
    ValidPattern.matcher(input).matches()


  def analyze(s: String): Unit =
    println(
      s"$s -> valid=${isValidIdentifier(s)}, splittable=${containsSplitPoints(s)}"
    )

  @main def runit()={

    isValidIdentifier("CamelCase") // true
    isValidIdentifier("snake_case") // true
    isValidIdentifier("mixedTest42") // true
    isValidIdentifier("_invalid") // false

  }
