package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam D owner of these buttons
 */

trait AddEditDeleteMoveUpMoveDown[D <: Dlg[?], A<:PwApp ] extends AddEditDelete[D,A] {
  self: D =>


  // buttons right
  @To()
  final val MoveUp = Btn[D,A]()(using ref) // move selected up ! in 1st line
  @To()
  final val MoveDown = Btn[D,A]()(using ref) // move selected down ! in last line
  def ref: MYDLG[D,A]
}
