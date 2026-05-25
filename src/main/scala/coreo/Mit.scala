package coreo


import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class Mit[D <: Dlg[?], A<:PwApp ](b: By = null)(using ref: MYDLG[D,A])
  extends Action[D,A](b) {
  override def ariaRole: AriaRole = AriaRole.MENUITEM
}
