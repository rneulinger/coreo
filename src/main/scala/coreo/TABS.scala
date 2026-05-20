package coreo

import coreo.*

/**
 * mixin for tabs
 */
trait TABS {
  self: Dlg =>

  given ref: OWNER[Dlg]
}
