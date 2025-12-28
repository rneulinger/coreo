package coreo

import com.microsoft.playwright.{Locator, Page}

case class CAL[F <: WIN](name: String, b: By = Loc.Default)(using ref: OWNER[F])
  extends DATA[F](b) {

  override def loc(pg: Page): Locator = ???
}
