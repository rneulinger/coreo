package coreo

import com.microsoft.playwright.*

import scala.jdk.CollectionConverters.*

class PwRoot(val baseUrl: String) extends ROOT {
  def nameOfApp = "No Name"

  def predefBaseUrls = Map[String, String]()

  def Self = this

  lazy val playwright: Playwright = Playwright.create()

  lazy val bOpts = new BrowserType.LaunchOptions().setHeadless(false)

  lazy val browser: Browser = playwright
    .chromium()
    .launch(bOpts)

  lazy val cOpts: Browser.NewContextOptions = new Browser.NewContextOptions()
    .setIgnoreHTTPSErrors(true)

  lazy val context: BrowserContext = browser.newContext(cOpts) // HTTPS-Fehler ignorieren

  lazy val pg: Page = {
    val tmp = context.newPage()
    tmp.navigate(baseUrl)
    tmp
  }

  // Cookies auslesen
  private lazy val cookie = context.cookies().asScala

  //  cookies.foreach(cookie => println(s"${cookie.name()} = ${cookie.value()}"))
  def close(): Unit = {
    browser.close()
    playwright.close()
  }

  def home: Response = pg.navigate(baseUrl)


  /**
   * current view
   */

  override def openUrl(path: String): Unit = pg.navigate(baseUrl + path)

  def gui: GUI = GUI(this)
}
