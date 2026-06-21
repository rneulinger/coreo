package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam D owner of these buttons
 */
//tag::fields[]
trait AddEditDelete[D <: Dlg] {
  self: D =>

  @TBD()
  final val Add = Btn[D]()(using myDlg) // new Dialog

  @TBD()
  final val Edit = Btn[D]()(using myDlg) // edit selected

  @TBD()
  final val Delete = Btn[D]()(using myDlg) // delete selected

  //end::fields[]
  given myDlg: D = scala.compiletime.deferred

}
