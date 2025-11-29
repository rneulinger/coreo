package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam F
 * has buttons Ok, Cancel
 */
trait OkCancel[F <: FRM, T <: FRM]() {
  self: F =>

  def ref: Own[F]

  final val Ok = BTN[F,T]()(using ref)
  final val Cancel = BTN[F,T]()(using ref)
}

