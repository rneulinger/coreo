package coreo

import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class RBT[F <: WIN](b: By = null)(using ref: OWNER[F])
  extends DATA[F](b) {
  override def ariaRole: AriaRole = AriaRole.RADIO
}
