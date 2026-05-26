package coreo

import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class Rbt[+D <: Dlg](by: By = null)(using dlg: D) extends Data:
  override def ariaRole: AriaRole = AriaRole.RADIO

