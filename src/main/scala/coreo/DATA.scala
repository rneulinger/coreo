package coreo

abstract class DATA[F <: WIN](b: By)(using ref: OWNER[F])
  extends ATOM[F](b) {
}