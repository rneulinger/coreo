package coreo.bricks

import coreo.*

trait CancelFinish[F <: WIN, C <: WIN, OK <: WIN]() {
  self: F =>

  def ref: OWNER[F]

  final val Cancel = BTN[F, C]()(using ref)
  final val Finish = BTN[F, OK]()(using ref)
}
