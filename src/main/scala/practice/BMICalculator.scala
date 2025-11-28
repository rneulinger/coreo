package practice

import coreo.*

final class BMICalculator(own: CanOwn) extends FRM(own, "BMI Calculator") {
  // tag::fields[]
  given ref: Own[BMICalculator] = Own(this)

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
