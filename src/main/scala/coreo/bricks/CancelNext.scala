package coreo.bricks

import coreo.*

//tag::fields[]
trait CancelNext[D <: Dlg]() {
  self: D =>

  @To(dest = classOf[Return])
  final val Cancel = Btn[D]()(using myDlg)

  @To()
  final val Next = Btn[D]()(using myDlg)

  //end::fields[]
  given myDlg: D = scala.compiletime.deferred
}
