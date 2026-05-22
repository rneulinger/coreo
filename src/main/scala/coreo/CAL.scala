package coreo

import com.microsoft.playwright.{Locator, Page}

class CAL[F <: Dlg](b: By = null)(using ref: F)
  extends Data[F](b) {

}
