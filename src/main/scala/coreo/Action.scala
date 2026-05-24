package coreo

abstract class Action[D <: ADlg](b: By)(using ref: D)
  extends Ctrl[D](b) {

  override def weight = 2

  var target: Static = Unknown_

//  lazy val action: Option[T] = None // TODO implement lookup

  override def click: ADlg =
    super.click
    if target != Unknown_ then
      ??? //own.onto(own.findWin(target))
    own

}