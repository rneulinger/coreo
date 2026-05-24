package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam F owner of these buttons
 */

trait AddEditDeleteMoveUpMoveDown[F <: ADlg] extends AddEditDelete[F] {
  self: F =>


  // buttons right
  @To()
  final val MoveUp = Btn[F]()(using ref) // move selected up ! in 1st line
  @To()
  final val MoveDown = Btn[F]()(using ref) // move selected down ! in last line
  def ref: F
}
