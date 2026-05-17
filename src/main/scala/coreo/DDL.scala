package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class DDL[F <: WIN](b: By = null)(using ref: OWNER[F])
  extends DATA[F](b) {
  override def ariaRole: AriaRole = AriaRole.LIST

  override def set(str: Any): F = {
    loc(pg).click()
    loc(pg).fill(str.toString)
    loc(pg).press("Enter")
    own
  }

}
