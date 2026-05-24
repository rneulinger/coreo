package coreo.bricks

import coreo.*

/**
 * Decorator Button 
 *
 * @tparam D owner of these buttons
 */
//tag::fields[]
trait AddEditDelete[D <: ADlg] {
  self: D =>

  @To()
  final val Add = Btn[D]()(using ref) // new Dialog
  @To()
  final val Edit = Btn[D]()(using ref) // edit selected
  @To()
  final val Delete = Btn[D]()(using ref) // delete selected
  //end::fields[]
  def ref: D

}
