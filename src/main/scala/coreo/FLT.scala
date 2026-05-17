package coreo

import com.microsoft.playwright.{Locator, Page}

/**
 * Filter toggle in table header
 */
import com.microsoft.playwright.options.AriaRole


class FLT[F <: WIN](b: By = null)(using ref: OWNER[F])
  extends DATA[F](b) {
  override def ariaRole: AriaRole = AriaRole.TEXTBOX

}
