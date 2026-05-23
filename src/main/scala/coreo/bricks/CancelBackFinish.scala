package coreo.bricks

import coreo.*

/**
 * Decorator Button 
 *
 * @tparam F
 * has buttons Cancel, Back Next
 */

trait CancelBackFinish[F <: Dlg]() {
  self: F =>

  @To(dest = classOf[Return])
  final val Cancel = Btn[F]()(using ref)
  @To()
  final val Back = Btn[F]()(using ref)
  @To(dest = classOf[Return])
  final val Finish = Btn[F]()(using ref)

  def ref: F
}
