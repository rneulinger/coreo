package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class CBX[F <: WIN](name: String, b: By = null)(using ref: OWNER[F])
  extends DATA[F](name, b) {
  override def ariaRole: AriaRole = AriaRole.COMBOBOX

  /*
  override def loc(pg: Page): Locator = {
    by match {
      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(fullName)
          .setExact(false)
        pg.getByRole(AriaRole.COMBOBOX, opt)
      case id: String => pg.locator(s"[id=\"$id\"]")

      case Loc.Label =>
        pg.getByLabel(fullName)

      case f: Function1[Page, Locator] =>
        f(pg)
    }
  }
  */


  //  def check: F = {
  //    println(s"")
  //    own
  //  }

  //  def uncheck: F =
  //    own

  override def set(str: Any): F = {
    loc(pg).click()
    val opt = new Page.GetByRoleOptions().setName("\uEA0F " + str.toString).setExact(true)
    pg.getByRole(AriaRole.OPTION, opt).click()
    own
  }
}

