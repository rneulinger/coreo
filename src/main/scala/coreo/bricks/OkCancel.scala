package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam D
 * has buttons Ok, Cancel
 */
trait OkCancel[D <: Dlg[?], A<:PwApp ]() {
  self: D =>


  @To(dest = classOf[Return])
  final val Ok = Btn[D,A]()(using ref)
  @To(dest = classOf[Return])
  final val Cancel = Btn[D,A]()(using ref)
  def ref: MYDLG[D,A]
}
