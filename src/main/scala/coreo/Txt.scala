package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Txt[F <: ADlg](b: By = null)(using ref: F)
  extends Data[F](b) {


  override def ariaRole: AriaRole = AriaRole.TEXTBOX

  def rightClick: F =
    log("rightClick")
    dlg

  def doubleClick: F =
    log("doubleClick")
    dlg

  def isEmpty: F =
    ???
    dlg

  def nonEmpty: F =
    ???
    dlg

  def maxLength(n: Integer): F =
    ???
    dlg

  def contains(snip: Any): F =
    ???
    dlg

  def matches(regex: String): F =
    ???
    dlg

  // assignment
  override def set(txt: Any = ""): F = {
    loc.fill(txt.toString)
    dlg
  }


  def <<(txt: Any = ""): F = {
    set(txt)
    dlg
  }

  /**
   * result to variable
   *
   * @return
   */
  def >> : F =
    dlg

  /**
   *
   * @return
   */
  def >>: : F =
    dlg

  // equality
  def :==(txt: Any = ""): F =
    dlg

  def :!=(txt: Any = ""): F =
    dlg

  // match
  def :=~(txt: Any = ""): F =
    dlg

  def :!~(txt: Any): F =
    dlg

  // rel ops textual
  def :<=(txt: Any): F =
    dlg

  def :<(txt: Any): F =
    dlg

  def :>=(txt: Any): F =
    dlg

  def :>(txt: Any): F =
    dlg

  // rel ops numerical
  def #<(txt: Any): F =
    dlg

  def #<=(txt: Any): F =
    dlg

  def #>=(txt: Any): F =
    dlg

  def #>(txt: Any): F =
    dlg
}
