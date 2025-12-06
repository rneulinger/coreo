package coreo

import com.microsoft.playwright.{Locator, Page}

case class RBT[F <: FRM](b: By = Loc.Default)(using ref: OWNER[F])
  extends DATA[F](b) {

  override def loc(pg: Page): Locator = ???
}
