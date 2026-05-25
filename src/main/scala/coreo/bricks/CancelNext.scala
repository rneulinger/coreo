package coreo.bricks

import coreo.*

trait CancelNext[D <: Dlg[?], A<:PwApp ]() {
  self: D =>


  @To(dest = classOf[Return])
  final val Cancel = Btn[D,A]()(using ref)
  @To()
  final val Next = Btn[D,A]()(using ref)

  def ref: MYDLG[D,A]
}
