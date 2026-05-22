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


  final val Add = Btn[F, A]()(using ref) // new Dialog
  final val Edit = Btn[F, E]()(using ref) // edit selected
  final val Delete = Btn[F, D]()(using ref) // delete selected
  //end::fields[]

  def ref: F
}
