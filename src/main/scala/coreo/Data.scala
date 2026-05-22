package coreo

abstract class Data[F <: Dlg](b: By)(using ref: F)
  extends Ctrl[F](b) {
}