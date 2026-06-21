package lab.practice

import lab.pw.*
import lab.utils
class PracticeApp extends App:// ("https://practice.expandtesting.com/") {
  //override def nameOfApp = "Practice"

  //override def predefBaseUrls = Map("Local" -> baseUrl)

  val Inputs = Inputs_()

  val BmiCalculator = BmiCalculator_()

  val Person = Person_()
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
    println( app.myObjs)
    println(app.Inputs.myObjs)
    println(app.Inputs.dataMembers)
    println(app.Inputs.actionMembers)
    println(app.BmiCalculator.ctrlMembers)
    println(app.Inputs.InputDate.myObjs)
    println( app.dlgMembers)

    println( "-"*10)

    println(app.Person.Cancel.classAnnotations)
    println(app.Person.Cancel.parentAnnotations)
    println(app.Person.Cancel.myAnnotations)
    println(app.Person.annotationsForObj(app.Person.Cancel))

    println( "-"*10)

    println(app.Person.classAnnotations.toList)
    println(app.Person.Cancel.parentAnnotations)
    println(app.Person.Ok.classAnnotations.toList)
    println(app.Person.annotationsForObj(app.Person.Ok))

  }