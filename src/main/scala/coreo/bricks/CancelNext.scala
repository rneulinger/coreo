package coreo.bricks

import coreo.*

trait CancelNext[F <: WIN, C <: WIN, N <: WIN]() {
  self: F =>

  def ref: OWNER[F]

  final val Cancel = BTN[F, C]()(using ref)
  final val Next = BTN[F, N]()(using ref)
}
