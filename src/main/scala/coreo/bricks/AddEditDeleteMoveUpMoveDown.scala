package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam D owner of these buttons
 */

trait AddEditDeleteMoveUpMoveDown[D <: ADlg] extends AddEditDelete[D] {
  self: D =>


  // buttons right
  @To()
  final val MoveUp = Btn[D]()(using ref) // move selected up ! in 1st line
  @To()
  final val MoveDown = Btn[D]()(using ref) // move selected down ! in last line
  def ref: D
}
