package coreo

abstract class ACTION[F <: FRM, T <: FRM](name:String, b: By)(using ref: OWNER[F])
  extends ATOM[F](name, b) {

  override def weight = 2

  var target:String=""
  lazy val action:Option[T] = None // TODO implememt lookup
}