package lab.practice

import lab.core.*
import lab.pw.{App, Dlg}

@Ui("xyz")
class Person_(using own: App) extends Dlg:
  val FirstName = TXT()
  val LastName  = TXT()
  @Tbd
  @To(classOf[Inputs_])
  val Ok = BTN()
  @Ret
  @To(classOf[BmiCalculator_])
  val Cancel = BTN()

