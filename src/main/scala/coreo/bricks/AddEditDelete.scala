package coreo.bricks

import coreo.*

/**
 * Decorator Button 
 *
 * @tparam F owner of these buttons
 */
//tag::fields[]
trait AddEditDelete[F <: Dlg] {
  self: F =>

  @To()
  final val Add = Btn[F]()(using ref) // new Dialog
  @To()
  final val Edit = Btn[F]()(using ref) // edit selected
  @To()
  final val Delete = Btn[F]()(using ref) // delete selected
  //end::fields[]

  def ref: F
}
