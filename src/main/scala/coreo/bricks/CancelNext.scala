package coreo.bricks

import coreo.*

trait CancelNext[F <: WIN, C <: WIN, N <: WIN]() {
  self: F =>

  def ref: OWNER[F]

  final val Cancel = Btn[F, C]()(using ref)
  final val Next = Btn[F, N]()(using ref)
}
