package coreo

abstract class ACTION[F <: FRM, T <: FRM](b: By)(using ref: Own[F])
  extends ATOM[F](b) {

  override def weight = 2

  var target:String=""
  lazy val action:Option[T] = None // TODO implememt lookup
}