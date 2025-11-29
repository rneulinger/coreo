package coreo.bricks

import coreo.*

/**
 * Decorator Button 
 *
 * @tparam F owner of these buttons
 */
trait AddEditDelete[F <: FRM, T <: FRM] {
  self: F =>

  def ref: Own[F]

  final val Add = BTN[F,T]()(using ref) // new Dialog
  final val Edit = BTN[F,T]()(using ref) // edit selected
  final val Delete = BTN[F,T]()(using ref) // delete selected
}
