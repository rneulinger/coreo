package coreo.bricks

import coreo.*

trait CancelFinish[F <: FRM, T<:FRM]() {
  self: F =>

  def ref: OWNER[F]

  final val Cancel = BTN[F,T]()(using ref)
  final val Finish = BTN[F,T]()(using ref)
}
