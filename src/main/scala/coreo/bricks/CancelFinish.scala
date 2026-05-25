package coreo.bricks

import coreo.*

trait CancelFinish[D <: Dlg[?], A<:PwApp ]() {
  self: D =>


  @To(dest = classOf[Return])
  final val Cancel = new Btn[D,A]()(using ref)
  @To(dest = classOf[Return])
  final val Finish = new Btn[D,A]()(using ref)
  def ref: MYDLG[D,A]
}
