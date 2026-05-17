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
class PWD_Repeat[F <: WIN](b: By = null)(using ref: OWNER[F])
  extends DATA[F]( b) {
  override def ariaRole: AriaRole = AriaRole.TEXTBOX

  //override def loc(pg: Page): Locator = ???
}