package coreo

abstract class Data[+D <: Dlg](by: By = null)(using dlg: D) extends Ctrl
