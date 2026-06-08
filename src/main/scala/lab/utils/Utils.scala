package lab.utils

def membersOfSubtype[A, T](a: A, target: Class[T]): List[(String, T)] =
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

  val methodMatches =
    methods.collect {
      case m if m.getParameterCount == 0 &&
        target.isAssignableFrom(m.getReturnType) =>
        m.setAccessible(true)
        m.getName -> m.invoke(a).asInstanceOf[T]
    }

  fieldMatches // ++ methodMatches


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

  val strings = membersOfSubtype(b, classOf[String])

  @main
  def test() = {
    println(strings)
  }


object fieldOfExactType:
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

  class Base:
    val base: String = "base"

  class Child extends Base:
    val child: String = "child"
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
