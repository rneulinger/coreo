package lab.practice

import lab.core.*
import lab.pw.{App, Dlg}

import scala.annotation.meta.field

@Ui("xyz")
class Person_(using own: App) extends Dlg:
  val FirstName = TXT()
  val LastName  = TXT()
  @(Tbd @field)
  @(Ui @field)("on field")
  @(To @field)(classOf[Inputs_])
  val Ok = BTN()
  @Ret
  @To(classOf[BmiCalculator_])
  val Cancel = BTN()

