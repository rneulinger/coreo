package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class SPBTN[F <: WIN](name: String, b: By = false)(using ref: OWNER[F])
  extends DATA[F](name, b) {
  override def ariaRole: AriaRole = AriaRole.SPINBUTTON

  /*
  override def loc(pg: Page): Locator = {
    by match {
      case id: String =>
        pg.locator(s"[id=\"$id\"]").getByRole(AriaRole.SPINBUTTON)

      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(fullName)
          .setExact(false)
        pg.getByRole(AriaRole.SPINBUTTON, opt)

      case f: Function1[Page, Locator] =>
        f(pg)
    }
  }
  */
  // asignment
  override def set(txt: Any = ""): F = {
    loc.fill(txt.toString)
    own
  }

}