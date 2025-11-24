import $ivy.`com.microsoft.playwright:playwright:1.53.0`
import $cp.`./build/libs/coreo.jar`
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*
import coreo.*

class Practice extends PwRoot("https://practice.expandtesting.com/"){

  override def nameOfApp = "Practice"

  override def predefBaseUrls = Map("Local" -> baseUrl)

  import com.microsoft.playwright.*
  import com.microsoft.playwright.options.*
  import coreo.*
  import coreo.bricks.*

  final class BMICalculator_(own: CanOwn) extends FRM(own, "BMI Calculator") {
    // tag::fields[]
    given ref: Own[BMICalculator_] = Own(this)

    // TODO set path if you can NAVIGATE directly to this page;  otherwise delete this
    override def path: String = "bmi"

    val Gender = TXT()
    val Age = TXT()
    val Height = TXT()
    val Weight = TXT()
    val Calculate = BTN()
    val Clear = BTN()

    // end::fields[]
  }


  val _BMICalculator = BMICalculator_(this)

  import coreo.*
  import coreo.bricks.*
  import com.microsoft.playwright.*
  import com.microsoft.playwright.options.*

  final class Inputs_(own: CanOwn) extends FRM(own) {
    // tag::fields[]
    given ref: Own[Inputs_] = Own(this)

    // TODO set path if you can NAVIGATE directly to this page;  otherwise delete this
    override def path: String = "inputs"

    val `Display Inputs` = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Display Inputs")))

    def DisplayInputs = `Display Inputs` // alias

    val `Clear Inputs` = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Clear Inputs")))

    def ClearInputs = `Clear Inputs` // alias

    val `Input: Number` = TXT(_.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Input: Number")))

    def InputNumber = `Input: Number` // alias

    val `Input: Text` = TXT(_.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Input: Text")))

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


  val _Inputs = Inputs_(this)


}

lazy val `/` = Practice()

//P.home