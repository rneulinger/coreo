package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam D owner of these buttons
 */

//tag::fields[]
trait AddEditDeleteMoveUpMoveDown[D <: Dlg] extends AddEditDelete[D] {
  self: D =>


  // buttons right
  @TBD()
  final val MoveUp = Btn[D]()(using myDlg) // move selected up ! in 1st line

  @TBD()
  final val MoveDown = Btn[D]()(using myDlg) // move selected down ! in last line

  //en::fields[]
  given myDlg: D = scala.compiletime.deferred
}
