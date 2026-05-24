package coreo

import com.microsoft.playwright.{Locator, Page}

class CAL[F <: ADlg](b: By = null)(using ref: F)
  extends Data[F](b) {

}
