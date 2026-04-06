package coreo

import com.microsoft.playwright.{Locator, Page}

/**
 * Filter toggle in table header
 */
import com.microsoft.playwright.options.AriaRole


class FLT[F <: WIN](name: String, b: By = false)(using ref: OWNER[F])
  extends DATA[F](name, b) {
  override def ariaRole: AriaRole = AriaRole.TEXTBOX

  /*
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
   */
}
