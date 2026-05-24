package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Btn[D <: Dlg[?]](b:By=null)(using ref: D)
  extends Action[D](b) {

  override def ariaRole: AriaRole = AriaRole.BUTTON
}
