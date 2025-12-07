package coreo

abstract class DATA[F <: FRM](name:String,b: By)(using ref: OWNER[F])
  extends ATOM[F](name,b) {
}