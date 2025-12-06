package coreo

abstract class DATA[F <: FRM](b: By)(using ref: OWNER[F])
  extends ATOM[F](b) {
}