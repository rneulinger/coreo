package coreo

abstract class ACTION[F <: WIN, T <: WIN](b: By)(using ref: OWNER[F])
  extends ATOM[F](b) {

  override def weight = 2

  var target: String = ""
  lazy val action: Option[T] = None // TODO implememt lookup
}