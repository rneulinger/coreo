package coreo


import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class ONOFF[F <: WIN](b: By = null)(using ref: OWNER[F])
  extends DATA[F](b) {
  override def ariaRole: AriaRole = AriaRole.BUTTON


  /*
  override def loc(pg: Page): Locator = {
    by match {
      case id: String => pg.locator(s"[id=\"$id\"]")
      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(fullName)
          .setExact(false)
        pg.getByRole(AriaRole.BUTTON, opt)

      case f: Function1[Page, Locator] =>
        f(pg)
    }
  }
*/
}
