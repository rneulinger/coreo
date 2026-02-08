package coreo

import coreo.*

/**
 * mixin for tabs
 */
trait TABS {
  self: WIN =>

  given ref: OWNER[WIN]
}
