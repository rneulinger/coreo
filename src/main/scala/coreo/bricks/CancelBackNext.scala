package coreo.bricks

import coreo.*

/**
 * Decorator Button 
 *
 * @tparam D
 * has buttons Cancel, Back Next
 */

trait CancelBackNext[D <: ADlg]() {
  self: D =>

  @To(dest = classOf[Return])
  final val Cancel = Btn[D]()(using ref)
  @To()
  final val Back = Btn[D]()(using ref)
  @To()
  final val Next = Btn[D]()(using ref)
  def ref: D
}
