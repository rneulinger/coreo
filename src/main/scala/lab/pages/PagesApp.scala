package lab.pages

import lab.pw.*
import lab.utils
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

val url = "https://testpages.eviltester.com/pages/"
class PagesApp extends App(url):// ("https://practice.expandtesting.com/") {
  override def instanceName = "EviltesterPw"

  val TextInputs = TextInputs_()
  given app:PagesApp = this


object PracticeApp:
  def apply(): PagesApp = {
    val app = PagesApp()
    app.validate()
    app
  }

  @main
  def ui()={
    val app = PagesApp()

    app.goTo( classOf[TextInputs_])
    app.click( "Text")
    app.set( "Text", "42")
    app.set( "Search", "Me")
    app.pause()
    java(app.pg)
    app.set( "InputNumber", "22")
    app.click( "Height" )
    //app.pause()
    app.click( "Weight" )
    app.set( "Height", "188")
    //app.set( "Weight", "90")
    app.click( "Clear")
    app.click( "Calculate" )
    app.goTo("bmi")
    app.goTo("examples")
    app.goTo("cars")
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

    println("-" * 10)


    println("-" * 10)


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