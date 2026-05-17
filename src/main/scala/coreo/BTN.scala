package coreo

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class BTN[F <: WIN, T <: WIN](name: String, b:By=null)(using ref: OWNER[F])
  extends ACTION[F, T](name, b) {

  override def ariaRole: AriaRole = AriaRole.BUTTON

  def this()(using ref: OWNER[F]) =
    this("")

}


object BTN {

  def apply[F <: WIN, T <: WIN](name: String="", b: By = null)(using ref: OWNER[F]):BTN[F,T] = {
    new BTN(name, b)(using ref)
  }
  def apply[F <: WIN, T <: WIN](b: By)(using ref: OWNER[F]):BTN[F,T] = {
    new BTN("", b)(using ref)
  }

}