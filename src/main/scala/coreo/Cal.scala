package coreo

import com.microsoft.playwright.{Locator, Page}

class Cal[D <: ADlg](b: By = null)(using ref: D)
  extends Data[D](b) {

}
