package coreo

abstract class Action[D <: Dlg[?], A<:PwApp ](b: By)(using ref: MYDLG[D,A])
  extends Ctrl[D,A](b) {

  override def weight = 2

  var target: Static = Unknown_

//  lazy val action: Option[T] = None // TODO implement lookup

  override def click: ADlg =
    super.click
    if target != Unknown_ then
      ??? //own.onto(own.findWin(target))
    dlg

}