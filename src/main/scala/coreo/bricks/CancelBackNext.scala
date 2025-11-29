package coreo.bricks

import coreo.*

/**
 * Decorator Button 
 *
 * @tparam F
 * has buttons Cancel, Back Next
 */

trait CancelBackNext[F <: FRM, T <: FRM]() {
  self: F =>

  def ref: Own[F]

  final val Cancel = BTN[F,T]()(using ref)
  final val Back = BTN[F,T]()(using ref)
  final val Next = BTN[F,T]()(using ref)
}
