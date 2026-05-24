package coreo.bricks

import coreo.*

trait CancelNext[D <: ADlg]() {
  self: D =>


  @To(dest = classOf[Return])
  final val Cancel = Btn[D]()(using ref)
  @To()
  final val Next = Btn[D]()(using ref)

  def ref: D
}
