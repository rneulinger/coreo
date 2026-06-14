package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam D
 * has buttons Cancel, Back Next
 */

//tag::fields[]
trait CancelBackNext[D <: Dlg]() {
  self: D =>

  @Ret
  final val Cancel = Btn[D]()(using myDlg)

  @Tbd()
  final val Back = Btn[D]()(using myDlg)

  @Tbd()
  final val Next = Btn[D]()(using myDlg)

  //end::fields[]
  given myDlg: D = scala.compiletime.deferred
}
