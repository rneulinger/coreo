package coreo

import com.microsoft.playwright.*

abstract class ATOM[F <: WIN](b: By)(using ref: OWNER[F])
  extends CHILD {
  final val own: F = ref.own
  var by: By = b

  final def app: PwApp = own.app.asInstanceOf[PwApp]

  def name: String

  final def uiName = if name.trim.isEmpty then fullName else name
  //println( "UiName:"+uiName)

  def setBy(b: By): Unit = {
    by = b
  }

  final def parentType: String = own.getClass.getSimpleName

  final def pg: Page = own.pg

  def loc(pg: Page): Locator

  def loc: Locator = loc(own.pg)

  def flash: F = {
    loc.evaluate("element => {" +
      "element.style.transition = 'background-color 0.3s ease';" +
      "element.style.backgroundColor = 'yellow';" +
      "setTimeout(() => element.style.backgroundColor = '', 500);" +
      "}")
    Thread.sleep(1000)
    own
  }

  /**
   * default locator
   *
   * @return
   */
  //def defaultLocator:Locator
  final def fullName: String = {

    //val allFields = own.getClass.getDeclaredFields
    val allFields = ReflectUtils.allInstanceFields(own.getClass)
    for (field <- allFields) {
      field.setAccessible(true)
      try {
        val value = field.get(own)
        if (value eq this) {
          //println("My name im parent is: " + field.getName)
          return field.getName
        }
      } catch {
        case e: IllegalAccessException =>
          e.printStackTrace()
      }
    }
    // search in base classes
    own.getClass
    "NOT FOUND"
  }

  def cleanName = shortName

  def shortName = Defs.mkCamelCase(fullName)

  def gen(value: Any) = s"${value.toString}..42"

  def random(value: String) = s"$value .. 42"

  own.adopt(this)

  def click: F =
    loc(pg).click()
    own

  final def clickFail: F =
    loc(pg).click()
    own

  final def click(cnt: Integer = 1): F = {
    own
  }

  def check: F =
    loc(pg).check()
    own

  def uncheck: F =
    loc(pg).uncheck()
    own

  def set(any: Any): F = {
    loc(pg).fill(any.toString)
    own
  }

  final def get(): F = {
    get(shortName)
  }

  def get(key: String): F = {
    val txt = loc.textContent()
    own.setVar(key, txt)
    own
  }

}


import java.lang.reflect.{Field, Modifier}

object ReflectUtils {

  /** Returns all declared fields from `clazz` and every superclass (excluding java.lang.Object).
   * By default, includes interface fields and makes each field accessible.
   *
   * @param clazz             The class to inspect
   * @param includeInterfaces Whether to include fields declared on interfaces (usually static finals)
   * @param makeAccessible    Whether to call setAccessible(true) on each field
   * @return Seq[Field]
   */
  def allDeclaredFields(
                         clazz: Class[_],
                         includeInterfaces: Boolean = true,
                         makeAccessible: Boolean = true
                       ): Seq[Field] = {

    val seen = scala.collection.mutable.Set[Field]()
    val acc = scala.collection.mutable.ArrayBuffer[Field]()

    // Walk superclasses
    var c: Class[_] = clazz
    while (c != null && c != classOf[Object]) {
      for (f <- c.getDeclaredFields) {
        if (makeAccessible) f.setAccessible(true)
        if (!seen.contains(f)) {
          acc += f
          seen += f
        }
      }
      c = c.getSuperclass
    }

    // Optionally include interface fields (typically public static final)
    if (includeInterfaces) {
      def visitInterfaces(cls: Class[_]): Unit = {
        for (intf <- cls.getInterfaces) {
          for (f <- intf.getDeclaredFields) {
            if (makeAccessible) f.setAccessible(true)
            if (!seen.contains(f)) {
              acc += f
              seen += f
            }
          }
          // Recurse into parent interfaces
          visitInterfaces(intf)
        }
      }

      visitInterfaces(clazz)
    }

    acc.toSeq
  }

  /** Convenience: only instance (non-static) fields */
  def allInstanceFields(clazz: Class[_]): Seq[Field] =
    allDeclaredFields(clazz).filterNot(f => Modifier.isStatic(f.getModifiers))
}
