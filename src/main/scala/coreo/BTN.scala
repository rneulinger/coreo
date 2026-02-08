package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class BTN[F <: WIN, T <: WIN](val name: String, b: By = Loc.Default)(using ref: OWNER[F])
  extends ACTION[F, T](b) {

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
}

object BTN {

  def apply[F <: WIN, T <: WIN](name: String, b: By = Loc.Default)(using ref: OWNER[F]):BTN[F,T] = {
    new BTN(name, b)(using ref)
  }
}