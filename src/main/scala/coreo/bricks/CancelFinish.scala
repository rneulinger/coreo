package coreo.bricks

import coreo.*

trait CancelFinish[F <: Dlg, C <: Dlg, OK <: Dlg]() {
  self: F =>


  final val Cancel = new Btn[F, C]()(using ref)
  final val Finish = new Btn[F, OK]()(using ref)
  def ref: F
}
