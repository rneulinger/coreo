package coreo.bricks

import coreo.*

/**
 * Decorator Button 
 *
 * @tparam F owner of these buttons
 */
//tag::fields[]
trait AddEditDelete[F <: WIN, A <: WIN, E <: WIN, D <: WIN] {
  self: F =>


  final val Add = BTN[F, A]("")(using ref) // new Dialog
  final val Edit = BTN[F, E]("")(using ref) // edit selected
  final val Delete = BTN[F, D]("")(using ref) // delete selected
  //end::fields[]

  def ref: OWNER[F]
}
