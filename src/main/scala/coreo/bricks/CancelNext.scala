package coreo.bricks

import coreo.*

trait CancelNext[F <: Dlg, C <: Dlg, N <: Dlg]() {
  self: F =>


  @To(Return)
  final val Cancel = Btn[F, C]()(using ref)
  @To(Overload)
  final val Next = Btn[F, N]()(using ref)

  def ref: F
}
