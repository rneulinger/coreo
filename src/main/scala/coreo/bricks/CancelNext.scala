package coreo.bricks

import coreo.*

//tag::fields[]
trait CancelNext[D <: Dlg]() {
  self: D =>

  @TBD
  final val Cancel = Btn[D]()(using myDlg)

  @TBD
  final val Next = Btn[D]()(using myDlg)

  //end::fields[]
  given myDlg: D = scala.compiletime.deferred
}
