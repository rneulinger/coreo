package coreo

import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

/**
 * password repeated 
 *
 * @param b
 * @param ref
 * @tparam F
 */
class PWD_Repeat[D <: Dlg[?], A<:PwApp ](b: By = null)(using ref: MYDLG[D,A])
  extends Data[D,A]( b) {
  override def ariaRole: AriaRole = AriaRole.TEXTBOX

  //override def loc(pg: Page): Locator = ???
}