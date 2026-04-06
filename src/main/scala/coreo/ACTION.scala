package coreo

abstract class ACTION[F <: WIN, T <: WIN](name:String, b: By)(using ref: OWNER[F])
  extends ATOM[F](name,b) {

  override def weight = 2

  var target: Static = Unknown_

  lazy val action: Option[T] = None // TODO implement lookup

  override def click: F =
    super.click
    if target != Unknown_ then
      own.onto(own.findWin(target))
    own

}