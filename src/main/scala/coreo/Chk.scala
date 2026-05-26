package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Chk[+D <: Dlg](by: By = null)(using dlg: D) extends Data:
  override def ariaRole: AriaRole = AriaRole.CHECKBOX

