package coreo

import com.microsoft.playwright.{Locator, Page}

class CAL[F <: Dlg](b: By = null)(using ref: OWNER[F])
  extends Data[F](b) {

}
