package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class BTN[F <: WIN, T <: WIN](b:By=null)(using ref: OWNER[F])
  extends ACTION[F, T](b) {

  override def ariaRole: AriaRole = AriaRole.BUTTON

}


object BTN {

  def apply[F <: WIN, T <: WIN](b: By = null)(using ref: OWNER[F]):BTN[F,T] = {
    new BTN(b)(using ref)
  }
}