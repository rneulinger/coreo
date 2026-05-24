package coreo

abstract class Action[F <: ADlg](b: By)(using ref: F)
  extends Ctrl[F](b) {

  override def weight = 2

  var target: Static = Unknown_

//  lazy val action: Option[T] = None // TODO implement lookup

  override def click: ADlg =
    super.click
    if target != Unknown_ then
      ??? //own.onto(own.findWin(target))
    own

}