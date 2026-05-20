package coreo.bricks

import coreo.*

trait CancelFinish[F <: WIN, C <: WIN, OK <: WIN]() {
  self: F =>

  def ref: OWNER[F]

  final val Cancel = new Btn[F, C]()(using ref)
  final val Finish = new Btn[F, OK]()(using ref)
}
