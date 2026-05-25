package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Ddl[D <: ADlg](b: By = null)(using ref: D)
  extends Data[D](b) {
  override def ariaRole: AriaRole = AriaRole.LIST

  override def set(str: Any): D = {
    loc(pg).click()
    loc(pg).fill(str.toString)
    loc(pg).press("Enter")
    dlg
  }

}
