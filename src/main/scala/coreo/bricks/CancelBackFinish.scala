package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam D
 * has buttons Cancel, Back Next
 */

//tag::fields[]
trait CancelBackFinish[D <: Dlg]() {
  self: D =>

  @Return
  final val Cancel = Btn[D]()(using myDlg)

  @TBD()
  final val Back = Btn[D]()(using myDlg)

  @Return
  final val Finish = Btn[D]()(using myDlg)

  //end::fields[]
  given myDlg: D = scala.compiletime.deferred
}
