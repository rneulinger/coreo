package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

case class BTN[F <: FRM,T <: FRM](name:String="", b: By = Loc.Default)(using ref: OWNER[F])
  extends ACTION[F,T](name,b) {

  override def loc(pg: Page): Locator = {
    by match {
      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(fullName)
          .setExact(false)
        pg.getByRole(AriaRole.BUTTON, opt)

      case f: Function1[Page, Locator] =>
        f(pg)
    }
  }

}
