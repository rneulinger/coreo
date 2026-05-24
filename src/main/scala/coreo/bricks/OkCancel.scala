package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam F
 * has buttons Ok, Cancel
 */
trait OkCancel[F <: ADlg]() {
  self: F =>


  @To(dest = classOf[Return])
  final val Ok = Btn[F]()(using ref)
  @To(dest = classOf[Return])
  final val Cancel = Btn[F]()(using ref)
  def ref: F
}
