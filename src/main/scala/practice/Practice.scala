package practice

import coreo.*

class Practice extends PwRoot("https://practice.expandtesting.com/"){
  override def nameOfApp = "Practice"

  override def predefBaseUrls = Map("Local" -> baseUrl)

  val _BMICalculator = BMICalculator(this)
  val _Inputs = Inputs(this)

}
