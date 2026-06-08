package practice

import coreo.*

class PracticeApp extends PwApp("https://practice.expandtesting.com/") {
  override def nameOfApp = "Practice"

  override def predefBaseUrls = Map("Local" -> baseUrl)

  val _BMICalculator = BMICalculator_()

  val _Inputs = Inputs_()


}
