package coreo

abstract class ACTION[F <: WIN, T <: WIN](b: By)(using ref: OWNER[F])
  extends Ctrl[F](b) {

  override def weight = 2

  var target: Static = Unknown_

  lazy val action: Option[T] = None // TODO implement lookup

  override def click: F =
    super.click
    if target != Unknown_ then
      own.onto(own.findWin(target))
    own

}