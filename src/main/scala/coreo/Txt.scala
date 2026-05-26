package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Txt[+D <: Dlg](by: By = null)(using dlg: D) extends Data:

  override def ariaRole: AriaRole = AriaRole.TEXTBOX

  def rightClick: D =
    log("rightClick")
    dlg

  def doubleClick: D =
    log("doubleClick")
    dlg

  def isEmpty: D =
    ???
    dlg

  def nonEmpty: D =
    ???
    dlg

  def maxLength(n: Integer): D =
    ???
    dlg

  def contains(snip: Any): D =
    ???
    dlg

  def matches(regex: String): D =
    ???
    dlg

  // assignment
  override def set(txt: Any = ""): D = {
    loc.fill(txt.toString)
    dlg
  }


  def <<(txt: Any = ""): D = {
    set(txt)
    dlg
  }

  /**
   * result to variable
   *
   * @return
   */
  def >> : D =
    dlg

  /**
   *
   * @return
   */
  def >>: : D =
    dlg

  // equality
  def :==(txt: Any = ""): D =
    dlg

  def :!=(txt: Any = ""): D =
    dlg

  // match
  def :=~(txt: Any = ""): D =
    dlg

  def :!~(txt: Any): D =
    dlg

  // rel ops textual
  def :<=(txt: Any): D =
    dlg

  def :<(txt: Any): D =
    dlg

  def :>=(txt: Any): D =
    dlg

  def :>(txt: Any): D =
    dlg

  // rel ops numerical
  def #<(txt: Any): D =
    dlg

  def #<=(txt: Any): D =
    dlg

  def #>=(txt: Any): D =
    dlg

  def #>(txt: Any): D =
    dlg

