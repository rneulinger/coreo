package coreo

import com.microsoft.playwright.{Locator, Page}

/**
 * Filter toggle in table header
 */
import com.microsoft.playwright.options.AriaRole


class Flt[+D <: Dlg](by: By = null)(using dlg: D) extends Data:
  override def ariaRole: AriaRole = AriaRole.TEXTBOX
