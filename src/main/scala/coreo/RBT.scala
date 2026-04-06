package coreo

import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class RBT[F <: WIN](name: String, b: By = false)(using ref: OWNER[F])
  extends DATA[F](name, b) {
  override def ariaRole: AriaRole = AriaRole.RADIO

  //override def loc(pg: Page): Locator = ???
}
