package lab.practice

import lab.pw.*
import lab.utils

class PracticeApp extends App("https://practice.expandtesting.com/"):// ("https://practice.expandtesting.com/") {
  //override def nameOfApp = "Practice"

  //override def predefBaseUrls = Map("Local" -> baseUrl)

  val Inputs = Inputs_()

  val BmiCalculator = BmiCalculator_()

  val Person = Person_()
  //

  val DialogOne = { class DialogOne_ extends Dlg with Interfaces.DialogOne_; DialogOne_() }

  val DialogTwo = new Dlg with Interfaces.DialogOne_ {}
  given app:PracticeApp = this


object PracticeApp:
  def apply(): PracticeApp = {
    val app = new PracticeApp()
    app.validate()
    app
  }

  @main
  def ui()={
    val app=PracticeApp()
    app.goTo("examples")
    app.goTo("cars")
    app.goTo("bmi")
    app.pause()
  }
  @main
  def main()={
    val app=PracticeApp()
    println( app.myObjs)
    println(app.Inputs.myObjs)
    println(app.Inputs.allDatas)
    println(app.Inputs.allActions)
    println(app.BmiCalculator.allCtrls)
    println(app.Inputs.InputDate.myObjs)
    println( app.allDlgs)

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

    var dlg = app.Person.Cancel.dest.isEmpty

    println( app.allDlgs)

  }