package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam F owner of these buttons
 */

trait AddEditDeleteMoveUpMoveDown[F <: WIN, A <: WIN, E <: WIN, D <: WIN, UP <: WIN, DOWN <: WIN] extends AddEditDelete[F, A, E, D] {
  self: F =>

  def ref: OWNER[F]

  // buttons right
  final val MoveUp = Btn[F, UP]()(using ref) // move selected up ! in 1st line
  final val MoveDown = Btn[F, DOWN]()(using ref) // move selected down ! in last line
}
