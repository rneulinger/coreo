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


  @TBD
  final val Ok = Btn[D]()(using myDlg)
  @TBD
  final val Cancel = Btn[D]()(using myDlg)

  given myDlg: D = scala.compiletime.deferred
}
