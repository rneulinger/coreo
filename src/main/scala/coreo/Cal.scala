package coreo

import com.microsoft.playwright.{Locator, Page}

class Cal[D <: Dlg[?], A<:PwApp ](b: By = null)(using ref: MYDLG[D,A])
  extends Data[D,A](b) {

}
