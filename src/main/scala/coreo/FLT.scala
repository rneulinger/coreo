package coreo

import com.microsoft.playwright.{Locator, Page}

/**
 * Filter toggle in table header
 */
import com.microsoft.playwright.options.AriaRole


class FLT[F <: Dlg](b: By = null)(using ref: OWNER[F])
  extends Data[F](b) {
  override def ariaRole: AriaRole = AriaRole.TEXTBOX

}
