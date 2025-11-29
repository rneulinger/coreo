package coreo.bricks

import coreo.*

trait NewChangDeleteDetailsCancelOK[F <: FRM, T <: FRM]() {
  self: F =>

  def ref: Own[F]

  final val New = BTN[F,T]()(using ref)
  final val Change = BTN[F,T]()(using ref)
  final val Delete = BTN[F,T]()(using ref)
  final val Details = BTN[F,T]()(using ref)
  final val OK = BTN[F,T]()(using ref)
  final val Cancel = BTN[F,T]()(using ref)
}
