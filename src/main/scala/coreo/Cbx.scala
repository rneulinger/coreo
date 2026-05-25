package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Cbx[D <: Dlg[?], A<:PwApp ](b: By = null)(using ref: MYDLG[D,A])
  extends Data[D,A](b) {
  override def ariaRole: AriaRole = AriaRole.COMBOBOX


  override def set(str: Any): D = {
    loc(pg).click()
    val opt = new Page.GetByRoleOptions().setName("\uEA0F " + str.toString).setExact(true)
    pg.getByRole(AriaRole.OPTION, opt).click()
    dlg
  }
}

