package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Chk[F <: Dlg](b: By = null)(using ref: F)
  extends Data[F](b) {
  override def ariaRole: AriaRole = AriaRole.CHECKBOX
}
