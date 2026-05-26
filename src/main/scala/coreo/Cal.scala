package coreo

import com.microsoft.playwright.{Locator, Page}

class Cal[+D <: Dlg](by: By = null)(using dlg: D) extends Data
