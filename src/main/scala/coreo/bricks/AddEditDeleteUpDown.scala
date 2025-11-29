package coreo.bricks

import coreo.*

trait AddEditDeleteUpDown[F <: FRM, T <: FRM] extends AddEditDelete[F,T] {
  self: F =>

  def ref: Own[F]

  // buttons right
  final val MoveUp = BTN[F,T]()(using ref) // move selected up ! in 1st line
  final val MoveDown = BTN[F,T]()(using ref) // move selected down ! in last line
}
