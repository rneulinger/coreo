package coreo

import com.microsoft.playwright.{Locator, Page}

/**
 * baseclass for all members of an application
 * that is app, dialogs and controls
 */
abstract class Obj {
  def pg: Page
  def app : AnyApp
  def myType: String = this.getClass.getSimpleName
    .reverse.dropWhile(_.toString == "_")
    .reverse.mkString("")

  protected def pre = {
    val cla = this.getClass
    val n = cla.getName
    val t = cla.getTypeName
    s"$n $t"
  }

  final def simple: String = this.getClass.getSimpleName

  def log(msg: Any) = println(pre + "." + msg)

  def page2Loc(l: Locator): Function1[Page, Locator] = new Function1[Page, Locator] {
    def apply(p: Page): Locator = l
  }
}
