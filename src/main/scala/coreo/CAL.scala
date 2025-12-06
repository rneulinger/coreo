package coreo

import com.microsoft.playwright.{Locator, Page}

case class CAL[F <: FRM](b: By = Loc.Default)(using ref: OWNER[F])
  extends DATA[F](b) {

  override def weight = 4

  override def loc(pg: Page): Locator = ???
}
