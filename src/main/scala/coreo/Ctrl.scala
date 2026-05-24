package coreo

import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

import java.util.regex.Pattern

abstract class Ctrl[F <: ADlg](b: By, idx:Int=0)(using dlg: F)
  extends Obj {
  def weight = 1
  def ariaRole:AriaRole = AriaRole.GENERIC
  final val own: F = dlg
  val name:String =  ???
  final def app: PwApp = own.app.asInstanceOf[PwApp]
  final def nameUi: String = if name.trim.isEmpty then fullName else name

  final var lfunc: (Page => Locator) = {
    b match{
      case null  => (p:Page) => p.getByText(nameUi)
      case func: (Page => Locator) => func
    }
  }

  //println( "UiName:"+uiName)

  final def setLoc(loc: Page => Locator): Unit = {
    this.lfunc = loc
  }

  final def parentType: String = own.getClass.getSimpleName

  final def pg: Page = own.pg

  final def loc(pg: Page): Locator = lfunc(pg)

  final def loc: Locator = loc(own.pg)

  def flash: ADlg = {
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

  def click: ADlg =
    loc(pg).click()
    own

  final def clickFail: ADlg =
    loc(pg).click()
    own

  final def click(cnt: Integer = 1): ADlg = {
    own
  }

  def check: ADlg =
    loc(pg).check()
    own

  def uncheck: ADlg =
    loc(pg).uncheck()
    own

  def set(any: Any): ADlg = {
    loc(pg).fill(any.toString)
    own
  }

  final def get(): ADlg = {
    get(shortName)
  }

  def get(key: String): ADlg = {
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
                         clazz: Class[?],
                         includeInterfaces: Boolean = true,
                         makeAccessible: Boolean = true
                       ): Seq[Field] = {

    val seen = scala.collection.mutable.Set[Field]()
    val acc = scala.collection.mutable.ArrayBuffer[Field]()

    // Walk superclasses
    var c: Class[?] = clazz
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
      def visitInterfaces(cls: Class[?]): Unit = {
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
  def allInstanceFields(clazz: Class[?]): Seq[Field] =
    allDeclaredFields(clazz).filterNot(f => Modifier.isStatic(f.getModifiers))
}

object Loc {
  def byText[F <: ADlg](idx:Int = 0):(Ctrl[F] => (Page => Locator) )
  = (ctrl:Ctrl[F]) => (p:Page) => p.getByText(ctrl.nameUi).nth(idx)
  def byPattern[F <: ADlg](pattern: Pattern, idx:Int = 0):(Ctrl[F] => (Page => Locator) )
  = (ctrl:Ctrl[F]) => (p:Page) => p.getByText(pattern).nth(idx)
  def byId[F <: ADlg](id:String ):(Ctrl[F] => (Page => Locator) )
  = (ctrl:Ctrl[F]) => (p:Page) => p.getByTestId(id)
  def byRole[F <: ADlg](idx:Int = 0 ):(Ctrl[F] => (Page => Locator) )
  = (ctrl:Ctrl[F]) => (p:Page) => p.getByRole(ctrl.ariaRole).nth(idx)
}
