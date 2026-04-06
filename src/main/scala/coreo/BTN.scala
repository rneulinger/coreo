package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class BTN[F <: WIN, T <: WIN](name: String, b:By=false)(using ref: OWNER[F])
  extends ACTION[F, T](name, b) {

  override def ariaRole: AriaRole = AriaRole.BUTTON

  /*
  override def loc(pg: Page): Locator = {
    by match {
      case id:String if id.trim.isEmpty =>
        val opt = Page.GetByRoleOptions()
          .setName(fullName)
          .setExact(false)
        pg.getByRole(AriaRole.BUTTON, opt)

      case id: String => pg.locator(s"[id=\"$id\"]")

      case f: Function1[Page, Locator] =>
        f(pg)
    }
  }
  */
}


object BTN {

  def apply[F <: WIN, T <: WIN](name: String="", b: By = false)(using ref: OWNER[F]):BTN[F,T] = {
    new BTN(name, b)(using ref)
  }
  def apply[F <: WIN, T <: WIN](b: By)(using ref: OWNER[F]):BTN[F,T] = {
    new BTN("", b)(using ref)
  }

}