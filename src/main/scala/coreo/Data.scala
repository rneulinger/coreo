package coreo

abstract class Data[F <: ADlg](b: By)(using ref: F)
  extends Ctrl[F](b) {
}