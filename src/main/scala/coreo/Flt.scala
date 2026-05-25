package coreo

import com.microsoft.playwright.{Locator, Page}

/**
 * Filter toggle in table header
 */
import com.microsoft.playwright.options.AriaRole


class Flt[D <: Dlg[?], A<:PwApp ](b: By = null)(using ref: MYDLG[D,A])
  extends Data[D,A](b) {
  override def ariaRole: AriaRole = AriaRole.TEXTBOX

}
