package lab.pw

import com.microsoft.playwright.*
import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}
import coreo.TBD
import lab.core.*
import lab.utils.*

type Loc = Page => Locator
type Adp = Ctrl => Loc

/**
 * base for all apps using playwright
 * @param baseUrl of the page
 *                default "" for testing purposes
 */
class App(val baseUrl : String="") extends _App:
  final val Unknown = new Dlg(using this){}
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

  override def goTo[T <: _Dlg](dlg: Class[T] | String = "/"): Unit = {
    dlg match {
      case url: String => pg.navigate(baseUrl + url)
      //case dlg :Dlg =>
    }
  }

  def pause() = {
    pg.pause()
  }


// playwright
class Dlg(using app: App) extends _Dlg:

  def wrap(loc: Loc): Adp = _ => loc

  def BTN(loc: Loc) = Btn(wrap(loc))(using this)
  def BTN(id:String=""): Btn = if id.isEmpty then Btn(null)(using this)
    else ??? // todo mkBtnById( id)

  def RET(loc: Loc) = RetBtn(wrap(loc))(using this)
  def RET(id:String=""): RetBtn = if id.isEmpty then RetBtn(null)(using this)
    else ???// todo mkRetById( id)

  def SUB(loc: Loc) = InToBtn(wrap(loc))(using this)
  def SUB(id:String=""): InToBtn = if id.isEmpty then InToBtn(null)(using this)
    else ??? //  todo mkSubById( id)

  def BAK(loc: Loc) = InToBtn(wrap(loc))(using this)
  def BAK(id:String=""): BackBtn = if id.isEmpty then BackBtn(null)(using this)
    else ??? // todo mkBackById(id)

  def NXT(loc: Loc) = InToBtn(wrap(loc))(using this)
  def NXT(id:String=""): NextBtn = if id.isEmpty then NextBtn(null)(using this)
    else ??? // todo mkToById(id)

  def TXT(loc: Loc) = Txt(wrap(loc))(using this)
  def TXT(id: String = ""): Txt = if id.isEmpty then Txt(null)(using this)
    else ??? //todo mkTxtById( id)


abstract class Ctrl(var loc: Adp)(using dlg: Dlg, app: App) extends _Ctrl:
  def click() = dlg

  def set(value: Any) = dlg

  def get(): String = ""

abstract class Data(loc: Adp)(using dlg: Dlg, app: App) extends Ctrl(loc) with _Data

abstract class Action(loc: Adp)(using dlg: Dlg, app: App) extends Ctrl(loc) with _Action

class Txt(loc: Adp)(using dlg: Dlg, app: App) extends Data(loc) with _Txt

@TBD
class Btn(loc: Adp)(using dlg: Dlg, app: App) extends Action(loc) with _Btn
class InToBtn(loc: Adp)(using dlg: Dlg, app: App) extends Btn(loc) with _InToBtn
class RetBtn(loc: Adp)(using dlg: Dlg, app: App) extends Btn(loc) with _RetBtn
class BackBtn(loc: Adp)(using dlg: Dlg, app: App) extends Btn(loc) with _BackBtn
class NextBtn(loc: Adp)(using dlg: Dlg, app: App) extends Btn(loc) with _NextBtn

trait MixIn(using app: App) extends _MixIn:
  self: Dlg =>

trait OkCancel(using app: App) extends _OkCancel:
  self: Dlg =>

trait CancelNextPrevious(using app: App) extends Dlg with PreviousNextCancel:
  self: Dlg =>

