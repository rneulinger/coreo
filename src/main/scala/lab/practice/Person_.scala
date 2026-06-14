package lab.practice

import lab.core.*
import lab.pw.{App, Dlg}

import scala.annotation.meta.field

@coreo.Ui("class annotation of Person")
@coreo.Ret
class Person_(using own: App) extends Dlg:
  val FirstName = TXT()
  val LastName  = TXT()
  @coreo.Tbd
  @coreo.Ui("on field")
  val Ok = BTN()
  @coreo.Ret
  @coreo.To(dest = classOf[BmiCalculator_])
  val Cancel = BTN()

