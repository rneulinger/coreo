package coreo.bricks

import coreo.*

trait CancelNext[F <: ADlg]() {
  self: F =>


  @To(dest = classOf[Return])
  final val Cancel = Btn[F]()(using ref)
  @To()
  final val Next = Btn[F]()(using ref)

  def ref: F
}
