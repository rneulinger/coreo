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
    val ti = app.TextInputs
    app.goTo(classOf[TextInputs_])
    app.goTo(ti)

    ti.Text.click()
    ti.Text.set(42)
    app.set( "Search", "Me")
    app.set( "Password", "password")
    app.pause()
    ti.Email.set("abc")
    ti.Url.set( "123")
    ti.Tel.set( "234345")
    //app.set( "Weight", "90")
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