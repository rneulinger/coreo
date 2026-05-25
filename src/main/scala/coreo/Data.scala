package coreo

abstract class Data[D <: Dlg[?], A<:PwApp ](b: By)(using ref: MYDLG[D,A])
  extends Ctrl[D,A](b) {
}