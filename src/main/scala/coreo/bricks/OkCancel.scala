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

  def ref: OWNER[F]

  final val Ok = Btn[F, OK]()(using ref)
  final val Cancel = Btn[F, C]()(using ref)
}
