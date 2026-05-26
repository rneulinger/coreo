package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Ddl[+D <: Dlg](by: By = null)(using dlg: D) extends Data:
  override def ariaRole: AriaRole = AriaRole.LIST

  override def set(str: Any): D = {
    loc(pg).click()
    loc(pg).fill(str.toString)
    loc(pg).press("Enter")
    dlg
  }
