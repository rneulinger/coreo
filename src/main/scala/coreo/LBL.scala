package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

case class LBL[F <: WIN](name: String, b: By = Loc.Default)(using ref: OWNER[F])
  extends DATA[F](b) {

  override def loc(pg: Page): Locator = {
    by match {
      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(fullName)
          .setExact(false)
        pg.getByRole(AriaRole.TEXTBOX, opt)

      case f: Function1[Page, Locator] =>
        f(pg)
    }
  }

}
