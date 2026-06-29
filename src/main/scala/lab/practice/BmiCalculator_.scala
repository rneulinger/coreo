package lab.practice

import coreo.GoTo
import lab.pw.*
import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.*
// tag::fields[]
@GoTo("bmi")
final class BmiCalculator_(using app: App) extends Dlg:

  // TODO set path if you can NAVIGATE directly to this page;  otherwise delete this
  //override def path: String = "bmi"

  val Gender = TXT()

  val Age = TXT(_.locator(""))

  val Height = SpinBTN(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Height (cm)")))

  val Weight = SpinBTN(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Weight (kg)")))

  val Calculate = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Calculate")))

  val Clear = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear")))

  val nodlg = "dkfshsdhf"

  // end::fields[]
  given dlg: BmiCalculator_ = this


