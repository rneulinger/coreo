package coreo.bricks

import coreo.*

/**
 * Decorator Button 
 *
 * @tparam F
 * has buttons Cancel, Back Next
 */

trait CancelBackNext[F <: FRM, C <: FRM, B <: FRM, N <: FRM]() {
  self: F =>

  def ref: OWNER[F]

  final val Cancel = BTN[F,C]()(using ref)
  final val Back = BTN[F,B]()(using ref)
  final val Next = BTN[F,N]()(using ref)
}
