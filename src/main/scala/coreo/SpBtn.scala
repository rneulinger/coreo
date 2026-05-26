package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class SpBtn[+D <: Dlg](by: By = null)(using dlg: D) extends Data:
  override def ariaRole: AriaRole = AriaRole.SPINBUTTON

  override def set(txt: Any = ""): D = {
    loc.fill(txt.toString)
    dlg
  }