package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam F
 * has buttons Ok, Cancel
 */
trait OkCancel[F <: Dlg, OK <: Dlg, C <: Dlg]() {
  self: F =>


  @To(Return)
  final val Ok = Btn[F, OK]()(using ref)
  @To(Return)
  final val Cancel = Btn[F, C]()(using ref)
  def ref: F
}
