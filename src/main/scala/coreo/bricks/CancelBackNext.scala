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

  @To(Return)
  final val Cancel = Btn[F, C]()(using ref)
  @To(Overload)
  final val Back = Btn[F, B]()(using ref)
  @To(Overload)
  final val Next = Btn[F, N]()(using ref)
  def ref: F
}
