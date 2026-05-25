package coreo

import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class RBT[D <: Dlg[?], A<:PwApp ](b: By = null)(using ref: MYDLG[D,A])
  extends Data[D,A](b) {
  override def ariaRole: AriaRole = AriaRole.RADIO
}
