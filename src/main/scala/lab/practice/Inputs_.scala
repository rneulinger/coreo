package lab.practice

import com.microsoft.playwright.*
import com.microsoft.playwright.options.*
import lab.pw.*

// tag::fields[]
@coreo.Go("inputs")
final class Inputs_(using own: App) extends Dlg:

  // TODO set path if you can NAVIGATE directly to this page;  otherwise delete this
  //override def path: String = 

  val DisplayInputs = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Display Inputs")))

  val ClearInputs = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear Inputs")))

  @coreo.Ui("Input: Number")
  val InputNumber = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Input: Number")))

  @coreo.Ui("Input: Text")
  val InputText = TXT(_.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Input: Text")))


  @coreo.Ui("Input: Password")
  val InputPassword = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Input: Password")))

  @coreo.Ui("Input: Date")
  val InputDate = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Input: Date")))

  @coreo.Ui("Output: Number")
  val OutputNumber = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Output: Number")))

  @coreo.Ui("Output: Text")
  val OutputText = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Output: Text")))

  @coreo.Ui("Output: Password")
  val OutputPassword = TXT(_.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Output: Password")))

  @coreo.Ui("Output: Date")
  val OutputDate = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Output: Date")))

  // end::fields[]
  given dlg: Inputs_ = this

