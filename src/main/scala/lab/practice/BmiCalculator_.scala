package lab.practice

import coreo.GoTo
import lab.pw.*

// tag::fields[]
@GoTo("bmi")
final class BmiCalculator_(using app: App) extends Dlg:

  // TODO set path if you can NAVIGATE directly to this page;  otherwise delete this
  //override def path: String = "bmi"

  val Gender = TXT()

  val Age = TXT(_.locator(""))

  val Height = TXT()

  val Weight = TXT()

  val Calculate = BTN()

  val Clear = BTN()

  val nodlg = "dkfshsdhf"

  // end::fields[]
  given dlg: BmiCalculator_ = this


