package coreo

import com.microsoft.playwright.{Locator, Page}

class CAL[F <: WIN](name: String, b: By = "")(using ref: OWNER[F])
  extends DATA[F](name, b) {

  //override def loc(pg: Page): Locator = ???
}
