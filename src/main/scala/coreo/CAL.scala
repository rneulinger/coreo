package coreo

import com.microsoft.playwright.{Locator, Page}

class CAL[F <: WIN](b: By = null)(using ref: OWNER[F])
  extends DATA[F](b) {

}
