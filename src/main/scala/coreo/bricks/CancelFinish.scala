package coreo.bricks

import coreo.*

//tag::fields[]
trait CancelFinish[D <: Dlg]() {
  self: D =>

  @Tbd
  final val Cancel = new Btn[D]()(using myDlg)

  @Tbd
  final val Finish = new Btn[D]()(using myDlg)

  //tag::fields[]
  given myDlg: D = scala.compiletime.deferred
}
