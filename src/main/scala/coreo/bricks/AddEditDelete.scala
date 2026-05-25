package coreo.bricks

import coreo.*

/**
 * Decorator Button 
 *
 * @tparam D owner of these buttons
 */
//tag::fields[]
trait AddEditDelete[D <: Dlg[?], A<:PwApp ] {
  self: D =>

  @To()
  final val Add = Btn[D,A]()(using ref) // new Dialog
  @To()
  final val Edit = Btn[D,A]()(using ref) // edit selected
  @To()
  final val Delete = Btn[D,A]()(using ref) // delete selected
  //end::fields[]
  def ref: MYDLG[D,A]

}
