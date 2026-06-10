package lab.practice

import lab.core.{Go, Interfaces}
import lab.pw.*

// tag::fields[]
@Go("BMI Calculator")
final class BMICalculator_(using app: App) extends Dlg:

  // TODO set path if you can NAVIGATE directly to this page;  otherwise delete this
  //override def path: String = "bmi"

  val Gender = TXT()

  val Age = TXT()

  val Height = TXT()

  val Weight = TXT()

  val Calculate = BTN()

  val Clear = BTN()

  // end::fields[]
  given dlg: BMICalculator_ = this


