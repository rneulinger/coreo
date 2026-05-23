package coreo.bricks

import coreo.*

trait CancelFinish[F <: Dlg]() {
  self: F =>


  @To(dest = classOf[Return])
  final val Cancel = new Btn[F]()(using ref)
  @To(dest = classOf[Return])
  final val Finish = new Btn[F]()(using ref)
  def ref: F
}
