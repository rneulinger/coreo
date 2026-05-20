package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Cbx[F <: Dlg](b: By = null)(using ref: OWNER[F])
  extends Data[F](b) {
  override def ariaRole: AriaRole = AriaRole.COMBOBOX


  override def set(str: Any): F = {
    loc(pg).click()
    val opt = new Page.GetByRoleOptions().setName("\uEA0F " + str.toString).setExact(true)
    pg.getByRole(AriaRole.OPTION, opt).click()
    own
  }
}

