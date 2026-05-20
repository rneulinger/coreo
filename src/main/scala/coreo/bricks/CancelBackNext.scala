package coreo.bricks

import coreo.*

/**
 * Decorator Button 
 *
 * @tparam F
 * has buttons Cancel, Back Next
 */

trait CancelBackNext[F <: Dlg, C <: Dlg, B <: Dlg, N <: Dlg]() {
  self: F =>

  def ref: OWNER[F]

  final val Cancel = Btn[F, C]()(using ref)
  final val Back = Btn[F, B]()(using ref)
  final val Next = Btn[F, N]()(using ref)
}
