package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class SPBTN[F <: WIN](b: By = null)(using ref: OWNER[F])
  extends DATA[F](b) {
  override def ariaRole: AriaRole = AriaRole.SPINBUTTON

  override def set(txt: Any = ""): F = {
    loc.fill(txt.toString)
    own
  }

}