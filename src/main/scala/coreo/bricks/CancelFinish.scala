package coreo.bricks

import coreo.*

//tag::fields[]
trait CancelFinish[D <: Dlg]() {
  self: D =>

  @To(dest = classOf[Return])
  final val Cancel = new Btn[D]()(using myDlg)

  @To(dest = classOf[Return])
  final val Finish = new Btn[D]()(using myDlg)

  //tag::fields[]
  given myDlg: D = scala.compiletime.deferred
}
