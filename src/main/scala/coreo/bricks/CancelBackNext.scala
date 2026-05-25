package coreo.bricks

import coreo.*

/**
 * Decorator Button 
 *
 * @tparam D
 * has buttons Cancel, Back Next
 */

trait CancelBackNext[D <: Dlg[?], A<:PwApp ]() {
  self: D =>

  @To(dest = classOf[Return])
  final val Cancel = Btn[D,A]()(using ref)
  @To()
  final val Back = Btn[D,A]()(using ref)
  @To()
  final val Next = Btn[D,A]()(using ref)
  def ref: MYDLG[D,A]
}
