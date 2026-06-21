package lab.practice

import coreo.{NextTo, Return, TBD}
import lab.core.*
import lab.pw.{App, Dlg}

import scala.annotation.meta.field

@coreo.Ui("class annotation of Person")
class Person_(using own: App) extends Dlg with Examples._Person

