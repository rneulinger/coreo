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

  @To(dest = classOf[Return])
  final val Cancel = Btn[D]()(using myDlg)

  @To()
  final val Back = Btn[D]()(using myDlg)

  @To(dest = classOf[Return])
  final val Finish = Btn[D]()(using myDlg)

  //end::fields[]
  given myDlg: D = scala.compiletime.deferred
}
