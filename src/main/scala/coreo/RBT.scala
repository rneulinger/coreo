package coreo

import com.microsoft.playwright.{Locator, Page}

case class RBT[F <: FRM](name:String, b: By = Loc.Default)(using ref: OWNER[F])
  extends DATA[F](name, b) {

  override def loc(pg: Page): Locator = ???
}
