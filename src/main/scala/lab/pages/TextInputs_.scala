package lab.pages

import com.microsoft.playwright.*
import com.microsoft.playwright.options.*
import coreo.GoTo
import lab.pw.*

// tag::fields[]
@GoTo("input-elements/text-inputs")
final class TextInputs_(using own: App) extends Dlg:

  val Text = TXT( _.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Text").setExact(true)))
  val Search = TXT( _.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search").setExact(true)))
  val Password = TXT( _.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password").setExact(true)))
  val Email = TXT( _.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email").setExact(true)))
  val Url = TXT( _.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Url").setExact(true)))
  val Tel = TXT( _.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("tel").setExact(true)))

  // end::fields[]
  given dlg: TextInputs_ = this

