package coreo


import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class MIT[F <: WIN, T <: WIN](name: String, b: By = false)(using ref: OWNER[F])
  extends ACTION[F, T](name, b) {
  override def ariaRole: AriaRole = AriaRole.MENUITEM

  /*
  override def loc(pg: Page): Locator = {
    by match {
      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(fullName)
          .setExact(false)
        pg.getByRole(AriaRole.MENUITEM, opt)

      case f: Function1[Page, Locator] =>
        f(pg)
    }
  }

   */
}
