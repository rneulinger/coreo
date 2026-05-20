package coreo

abstract class Data[F <: Dlg](b: By)(using ref: OWNER[F])
  extends Ctrl[F](b) {
}