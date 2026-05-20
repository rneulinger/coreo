package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Btn[F <: Dlg, T <: Dlg](b:By=null)(using ref: OWNER[F])
  extends Action[F, T](b) {

  override def ariaRole: AriaRole = AriaRole.BUTTON
}
