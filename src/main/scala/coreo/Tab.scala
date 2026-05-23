package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Tab[F <: Dlg](b: By = null)(using ref: F)
  extends Action[F](b) {
  override def ariaRole: AriaRole = AriaRole.TAB
}
