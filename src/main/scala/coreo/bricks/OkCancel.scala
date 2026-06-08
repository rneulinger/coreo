package coreo.bricks

import coreo.*

/**
 * Decorator Button
 *
 * @tparam D
 * has buttons Ok, Cancel
 */
trait OkCancel[D <: Dlg]() {
  self: D =>


  @To(dest = classOf[Return])
  final val Ok = Btn[D]()(using myDlg)
  @To(dest = classOf[Return])
  final val Cancel = Btn[D]()(using myDlg)

  given myDlg: D = scala.compiletime.deferred
}
