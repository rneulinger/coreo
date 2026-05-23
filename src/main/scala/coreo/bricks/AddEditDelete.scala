package coreo.bricks

import coreo.*

/**
 * Decorator Button 
 *
 * @tparam F owner of these buttons
 */
//tag::fields[]
trait AddEditDelete[F <: Dlg, A <: Dlg, E <: Dlg, D <: Dlg] {
  self: F =>

  @To(Overload)
  final val Add = Btn[F, A]()(using ref) // new Dialog
  @To(Overload)
  final val Edit = Btn[F, E]()(using ref) // edit selected
  @To(Overload)
  final val Delete = Btn[F, D]()(using ref) // delete selected
  //end::fields[]

  def ref: F
}
