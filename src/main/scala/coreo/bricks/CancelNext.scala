package coreo.bricks

import coreo.*

trait CancelNext[F <: Dlg, C <: Dlg, N <: Dlg]() {
  self: F =>

  def ref: OWNER[F]

  final val Cancel = Btn[F, C]()(using ref)
  final val Next = Btn[F, N]()(using ref)
}
