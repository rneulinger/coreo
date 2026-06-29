package lab.practice

import lab.pw.*
import lab.utils
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

val url = "https://practice.expandtesting.com/"
class PracticeApp extends App(url):// ("https://practice.expandtesting.com/") {
  override def instanceName = "PracticePw"

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
    app.goTo( classOf[BmiCalculator_])
    java(app.pg)
    //app.goTo( classOf[Inputs_])
    //app.set( "InputNumber", "22")
    app.click( "Height" )
    //app.pause()
    app.click( "Weight" )
    app.set( "Height", "188")
    //app.set( "Weight", "90")
    app.click( "Clear")
    app.click( "Calculate" )
//    app.goTo("bmi")
//    app.goTo("examples")
//    app.goTo("cars")
    //app.pause()
    app.context.close()
    app.context.browser().close()
    app.playwright.close()
    app.report(1)
    app.debug("sdfssdf")
  }
  @main
  def main()= {
    val app = PracticeApp()
    println(app.myObjs)
    println(app.Inputs.myObjs)
    println(app.Inputs.allDatas)
    println(app.Inputs.allActions)
    println(app.BmiCalculator.allCtrls)
    println(app.Inputs.InputDate.myObjs)
    println(app.allDlgs)

    println("-" * 10)

    println(app.Person.Cancel.classAnnotations)
    println(app.Person.Cancel.parentAnnotations)
    println(app.Person.Cancel.myAnnotations)
    println(app.Person.annotationsForObj(app.Person.Cancel))

    println("-" * 10)

    println(app.Person.classAnnotations.toList)
    println(app.Person.Cancel.parentAnnotations)
    println(app.Person.Ok.classAnnotations.toList)
    println(app.Person.annotationsForObj(app.Person.Ok))

    var dlg = app.Person.Cancel.dest.isEmpty

    println(app.allDlgs)
  }

  def java(page:Page) = {
      page.navigate("https://practice.expandtesting.com/bmi");
      var loc = page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Height (cm)"))
      println( loc )
      loc.click()
      loc = page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Height (cm)"))
      println( loc )
      loc.fill("200")
      loc = page.getByRole(AriaRole.SPINBUTTON, new Page.GetByRoleOptions().setName("Height (cm)"))
      println (loc )
      loc.press("Tab");
  }