package lab.practice

import lab.pw.*
import lab.utils
class PracticeApp extends App:// ("https://practice.expandtesting.com/") {
  //override def nameOfApp = "Practice"

  //override def predefBaseUrls = Map("Local" -> baseUrl)

  val _Inputs = Inputs_()

  val _BmiCalculator = BmiCalculator_()

  val _Person = Person_()
  given app:PracticeApp = this


object PracticeApp:
  def apply(): PracticeApp = {
    val app = new PracticeApp()
    app.validate()
    app
  }
  @main
  def main()={
    val app=PracticeApp()
    println( app.objMembers)
    println(app._Inputs.objMembers)
    println(app._Inputs.dataMembers)
    println(app._Inputs.actionMembers)
    println(app._BmiCalculator.ctrlMembers)
    println(app._Inputs.InputDate.objMembers)
    println( app.dlgMembers)

    println( "-"*10)

    println(app._Person.classAnnotations().toList)
    println(app._Person.Ok.classAnnotations().toList)
    println(app._Person.objAnnotations(app._Person.Ok))
  }