package coreo

abstract class Data[D <: ADlg](b: By)(using ref: D)
  extends Ctrl[D](b) {
}