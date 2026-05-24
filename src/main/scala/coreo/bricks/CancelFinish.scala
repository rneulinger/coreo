package coreo.bricks

import coreo.*

trait CancelFinish[D <: ADlg]() {
  self: D =>


  @To(dest = classOf[Return])
  final val Cancel = new Btn[D]()(using ref)
  @To(dest = classOf[Return])
  final val Finish = new Btn[D]()(using ref)
  def ref: D
}
