package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Tab[+D <: Dlg](by: By = null)(using dlg: D) extends Action:
  override def ariaRole: AriaRole = AriaRole.TAB

