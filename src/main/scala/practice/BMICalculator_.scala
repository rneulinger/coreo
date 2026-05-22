package practice

import coreo.*

// tag::fields[]
final class BMICalculator_(own: AnyApp) extends Dlg(own, "BMI Calculator") {

  // TODO set path if you can NAVIGATE directly to this page;  otherwise delete this
  override def path: String = "bmi"

  val Gender = TXT()

  val Age = TXT()

  val Height = TXT()

  val Weight = TXT()

  val Calculate = BTN()

  val Clear = BTN()

  // end::fields[]
  given ref: BMICalculator_ = this
}
