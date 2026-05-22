package coreo.bricks

import coreo.*

trait NewChangDeleteDetailsCancelOK[F <: Dlg]() {
  self: F =>

  def ref: F

  //  final val New = BTN[F]()(using ref)
  //  final val Change = BTN[F]()(using ref)
  //  final val Delete = BTN[F]()(using ref)
  //  final val Details = BTN[F]()(using ref)
  //  final val OK = BTN[F]()(using ref)
  //  final val Cancel = BTN[F]()(using ref)
}
