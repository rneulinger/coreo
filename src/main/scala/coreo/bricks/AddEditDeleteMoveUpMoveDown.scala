package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam F owner of these buttons
 */

trait AddEditDeleteMoveUpMoveDown[F <: FRM, A <: FRM, E <: FRM, D <: FRM, UP <: FRM, DOWN <: FRM] extends AddEditDelete[F,A,E,D] {
  self: F =>

  def ref: OWNER[F]

  // buttons right
  final val MoveUp = BTN[F,UP]("")(using ref) // move selected up ! in 1st line
  final val MoveDown = BTN[F,DOWN]("")(using ref) // move selected down ! in last line
}
