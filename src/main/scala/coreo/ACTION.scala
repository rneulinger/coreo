package coreo

abstract class ACTION[F <: FRM, T <: FRM](b: By)(using ref: Own[F])
  extends ATOM[F](b) {

  def action:Option[T] = None
}