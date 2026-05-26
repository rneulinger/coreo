package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Cbx[+D <: Dlg](by: By = null)(using dlg: D) extends Data:

  override def ariaRole: AriaRole = AriaRole.COMBOBOX

  override def set(str: Any): D = {
    loc(pg).click()
    val opt = new Page.GetByRoleOptions().setName("\uEA0F " + str.toString).setExact(true)
    pg.getByRole(AriaRole.OPTION, opt).click()
    dlg
  }

