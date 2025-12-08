package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam F
 * has buttons Ok, Cancel
 */
trait OkCancel[F <: FRM, OK<:FRM, C <: FRM]() {
  self: F =>

  def ref: OWNER[F]

  final val Ok = BTN[F,OK]("")(using ref)
  final val Cancel = BTN[F,C]("")(using ref)
}
