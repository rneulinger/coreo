package coreo


import com.microsoft.playwright.{Locator, Page}
import com.microsoft.playwright.options.AriaRole

case class MIT[F <: FRM, T <: FRM](b: By = Loc.Default)(using ref: Own[F])
  extends ACTION[F,T](b) {
  override def loc(pg: Page): Locator = {
    by match {
      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(cleanName)
          .setExact(false)
        pg.getByRole(AriaRole.MENUITEM, opt)

      case f: Function1[Page, Locator] =>
        f(pg)
    }
  }
}
