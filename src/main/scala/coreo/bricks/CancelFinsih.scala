package coreo.bricks

import coreo.*

/**
 * has buttons Cancel and Finish
 */
trait CancelFinsih[F <: FRM, T <: FRM]() {
  self: F =>

  def ref: Own[F]

  final val Cancel = BTN[F,T]()(using ref)
  final val Finish = BTN[F,T]()(using ref)
}
