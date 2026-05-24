package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Chk[D <: ADlg](b: By = null)(using ref: D)
  extends Data[D](b) {
  override def ariaRole: AriaRole = AriaRole.CHECKBOX
}
