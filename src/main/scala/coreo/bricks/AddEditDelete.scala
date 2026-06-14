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

  @Tbd()
  final val Add = Btn[D]()(using myDlg) // new Dialog

  @Tbd()
  final val Edit = Btn[D]()(using myDlg) // edit selected

  @Tbd()
  final val Delete = Btn[D]()(using myDlg) // delete selected

  //end::fields[]
  given myDlg: D = scala.compiletime.deferred

}
