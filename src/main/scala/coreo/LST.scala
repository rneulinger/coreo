package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class LST[F <: Dlg](b: By = null)(using ref: OWNER[F])
  extends Data[F](b) {
  override def ariaRole: AriaRole = AriaRole.LISTBOX

}
