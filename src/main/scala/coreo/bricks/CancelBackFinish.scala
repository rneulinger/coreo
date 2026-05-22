package coreo.bricks

import coreo.*

/**
 * Decorator Button 
 *
 * @tparam F
 * has buttons Cancel, Back Next
 */

trait CancelBackFinish[F <: Dlg, C <: Dlg, B <: Dlg, N <: Dlg]() {
  self: F =>

  final val Cancel = Btn[F, C]()(using ref)
  final val Back = Btn[F, B]()(using ref)
  final val Finish = Btn[F, N]()(using ref)

  def ref: F
}
