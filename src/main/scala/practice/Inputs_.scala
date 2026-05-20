package practice

import com.microsoft.playwright.*
import com.microsoft.playwright.options.*
import coreo.*
import coreo.bricks.*

final class Inputs_(own: CanOwn) extends Dlg(own) {
  // tag::fields[]
  given ref: OWNER[Inputs_] = OWNER(this)

  // TODO set path if you can NAVIGATE directly to this page;  otherwise delete this
  override def path: String = "inputs"

  val DisplayInputs = BTN((p:Page) => p.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Display Inputs")))

  val ClearInputs = BTN((p:Page) => p.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear Inputs")))

  @Ui("Input: Number")
  val InputNumber = TXT( _.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Input: Number")))

  @Ui("Input: Text")
  val InputText = TXT( _.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Input: Text")))


  @Ui("Input: Password")
  val InputPassword = TXT( _.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Input: Password")))

  @Ui("Input: Date")
  val InputDate = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Input: Date")))

  @Ui("Output: Number")
  val OutputNumber = TXT( (p:Page) => p.
    getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Output: Number")))

  @Ui("Output: Text")
  val OutputText = TXT((p:Page) => p.
    getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Output: Text")))

  @Ui("Output: Password")
  val OutputPassword = TXT( (p:Page) => p.
    getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Output: Password")))

  @Ui("Output: Date")
  val OutputDate = TXT( (p:Page) => p.
    getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Output: Date")))

  // end::fields[]
}
