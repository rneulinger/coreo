package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam D
 * has buttons Ok, Cancel
 */
trait OkCancel[D <: ADlg]() {
  self: D =>


  @To(dest = classOf[Return])
  final val Ok = Btn[D]()(using ref)
  @To(dest = classOf[Return])
  final val Cancel = Btn[D]()(using ref)
  def ref: D
}
