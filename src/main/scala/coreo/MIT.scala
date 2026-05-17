package coreo


import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class MIT[F <: WIN, T <: WIN](b: By = null)(using ref: OWNER[F])
  extends ACTION[F, T](b) {
  override def ariaRole: AriaRole = AriaRole.MENUITEM
}
