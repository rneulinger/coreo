package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class LST[F <: WIN](name: String, b: By = false)(using ref: OWNER[F])
  extends DATA[F](name, b) {
  override def ariaRole: AriaRole = AriaRole.LISTBOX

  /*
  override def loc(pg: Page): Locator = {
    by match {
      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(fullName)
          .setExact(false)
        pg.getByRole(AriaRole.LISTBOX, opt)

      case f: Function1[Page, Locator] =>
        f(pg)
    }
  }

   */
}
