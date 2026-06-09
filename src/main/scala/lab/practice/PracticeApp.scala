package lab.practice

import lab.pw.*
import lab.utils
class PracticeApp extends App:// ("https://practice.expandtesting.com/") {
  //override def nameOfApp = "Practice"

  //override def predefBaseUrls = Map("Local" -> baseUrl)

  val _Inputs = Inputs_()

  val _BmiCalculator = BMICalculator_()

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
    println(app._Inputs.ctrls)
    println(app._Inputs.datas)
    println(app._Inputs.actions)
    println(app._BmiCalculator.ctrls)
  }