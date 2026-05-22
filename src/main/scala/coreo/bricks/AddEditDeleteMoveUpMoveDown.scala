package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam F owner of these buttons
 */

trait AddEditDeleteMoveUpMoveDown[F <: Dlg, A <: Dlg, E <: Dlg, D <: Dlg, UP <: Dlg, DOWN <: Dlg] extends AddEditDelete[F, A, E, D] {
  self: F =>


  // buttons right
  final val MoveUp = Btn[F, UP]()(using ref) // move selected up ! in 1st line
  final val MoveDown = Btn[F, DOWN]()(using ref) // move selected down ! in last line
  def ref: F
}
