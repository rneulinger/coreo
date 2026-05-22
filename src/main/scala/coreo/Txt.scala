package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Txt[F <: Dlg](b: By = null)(using ref: F)
  extends Data[F](b) {


  override def ariaRole: AriaRole = AriaRole.TEXTBOX

  def rightClick: F =
    log("rightClick")
    own

  def doubleClick: F =
    log("doubleClick")
    own

  def isEmpty: F =
    ???
    own

  def nonEmpty: F =
    ???
    own

  def maxLength(n: Integer): F =
    ???
    own

  def contains(snip: Any): F =
    ???
    own

  def matches(regex: String): F =
    ???
    own

  // assignment
  override def set(txt: Any = ""): F = {
    loc.fill(txt.toString)
    own
  }


  def <<(txt: Any = ""): F = {
    set(txt)
    own
  }

  /**
   * result to variable
   *
   * @return
   */
  def >> : F =
    own

  /**
   *
   * @return
   */
  def >>: : F =
    own

  // equality
  def :==(txt: Any = ""): F =
    own

  def :!=(txt: Any = ""): F =
    own

  // match
  def :=~(txt: Any = ""): F =
    own

  def :!~(txt: Any): F =
    own

  // rel ops textual
  def :<=(txt: Any): F =
    own

  def :<(txt: Any): F =
    own

  def :>=(txt: Any): F =
    own

  def :>(txt: Any): F =
    own

  // rel ops numerical
  def #<(txt: Any): F =
    own

  def #<=(txt: Any): F =
    own

  def #>=(txt: Any): F =
    own

  def #>(txt: Any): F =
    own
}
