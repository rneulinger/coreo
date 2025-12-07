package practice

import com.microsoft.playwright.*
import com.microsoft.playwright.options.*
import coreo.*
import coreo.bricks.*

final class Inputs_(own: CanOwn) extends FRM(own) {
  // tag::fields[]
  given ref: OWNER[Inputs_] = OWNER(this)

  // TODO set path if you can NAVIGATE directly to this page;  otherwise delete this
  override def path: String = "inputs"

  val DisplayInputs = BTN("Display Inputs",
    _.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Display Inputs")))

  val ClearInputs = BTN("Clear Inputs",
    _.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear Inputs")))

  val InputNumber = TXT("Input: Number",
    _.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Input: Number")))

  val InputText = new TXT("Input: Text",
    _.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Input: Text"))){
    override def gen(any: Any): String = {
       s"$any..42"
    }
  }

  val InputPassword = TXT("Input: Password",
    _.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Input: Password")))

  val InputDate = TXT("Input: Date",
    _.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Input: Date")))

  val OutputNumber = TXT("Output: Number",
    _.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Output: Number")))

  val OutputText = TXT("Output: Text",
    _.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Output: Text")))

  val OutputPassword = TXT("Output: Password",
    _.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Output: Password")))

  val OutputDate = TXT("Output: Date",
    _.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Output: Date")))

  // end::fields[]
}
