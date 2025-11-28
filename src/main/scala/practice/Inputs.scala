package practice

import com.microsoft.playwright.*
import com.microsoft.playwright.options.*
import coreo.*
import coreo.bricks.*

final class Inputs(own: CanOwn) extends FRM(own) {
  // tag::fields[]
  given ref: Own[Inputs] = Own(this)

  // TODO set path if you can NAVIGATE directly to this page;  otherwise delete this
  override def path: String = "inputs"

  val `Display Inputs` = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Display Inputs")))

  def DisplayInputs = `Display Inputs` // alias

  val `Clear Inputs` = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear Inputs")))

  def ClearInputs = `Clear Inputs` // alias

  val `Input: Number` = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Input: Number")))

  def InputNumber = `Input: Number` // alias

  val `Input: Text` = new TXT(_.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Input: Text"))){
    override def gen(any: Any): String = {
       s"$any..42"
    }
  }

  def InputText = `Input: Text` // alias

  val `Input: Password` = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Input: Password")))

  def InputPassword = `Input: Password` // alias

  val `Input: Date` = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Input: Date")))

  def InputDate = `Input: Date` // alias

  val `Output: Number` = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Output: Number")))

  def OutputNumber = `Output: Number` // alias

  val `Output: Text` = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Output: Text")))

  def OutputText = `Output: Text` // alias

  val `Output: Password` = TXT(_.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Output: Password")))

  def OutputPassword = `Output: Password` // alias

  val `Output: Date` = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Output: Date")))

  def OutputDate = `Output: Date` // alias

  // end::fields[]
}
