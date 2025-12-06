package coreo

import com.microsoft.playwright.{Locator, Page}

/**
 * password repeated 
 *
 * @param b
 * @param ref
 * @tparam F
 */
case class PWD_Repeat[F <: FRM](b: By = Loc.Default)(using ref: OWNER[F])
  extends DATA[F](b) {

  override def loc(pg: Page): Locator = ???
}