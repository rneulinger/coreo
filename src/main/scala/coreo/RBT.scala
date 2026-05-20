package coreo

import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class RBT[F <: Dlg](b: By = null)(using ref: OWNER[F])
  extends Data[F](b) {
  override def ariaRole: AriaRole = AriaRole.RADIO
}
