package coreo.bricks

import coreo.*

//tag::fields[]
trait CancelNext[D <: Dlg]() {
  self: D =>

  @Tbd
  final val Cancel = Btn[D]()(using myDlg)

  @Tbd
  final val Next = Btn[D]()(using myDlg)

  //end::fields[]
  given myDlg: D = scala.compiletime.deferred
}
