package coreo

abstract   class Action[+D <: Dlg](by: By = null)(using dlg: D) extends Ctrl
  {

  override def weight = 2

  var target: Static = Unknown_

//  lazy val action: Option[T] = None // TODO implement lookup

  override def click: Dlg =
    super.click
    if target != Unknown_ then
      ??? //own.onto(own.findWin(target))
    dlg

}