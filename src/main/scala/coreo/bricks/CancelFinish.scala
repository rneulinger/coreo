package coreo.bricks

import coreo.*

//tag::fields[]
trait CancelFinish[D <: Dlg]() {
  self: D =>

  @TBD
  final val Cancel = new Btn[D]()(using myDlg)

  @TBD
  final val Finish = new Btn[D]()(using myDlg)

  //tag::fields[]
  given myDlg: D = scala.compiletime.deferred
}
