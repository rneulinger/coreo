package lab.pw

import com.microsoft.playwright.*
import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}
import coreo.TBD
import lab.core.*
import lab.utils.*

type Loc = Page => Locator
type Adp = Ctrl => Loc

def wrap( loc: Loc ):Adp = (ctrl:Ctrl) => loc
/**
 * base for all apps using playwright
 * @param baseUrl of the page
 *                default "" for testing purposes
 */
class App(val baseUrl : String="") extends _App:
  def instanceName = "PwApp"
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
  def navigate(path:String)=
    pg.navigate(baseUrl+path)

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

  def SpinBTN(loc: Loc) = SpinBtn(wrap(loc))(using this)
  def SpinBTN(id: String = ""): SpinBtn = if id.isEmpty then SpinBtn(null)(using this)
  else ??? //todo mkTxtById( id)


/**
 *
 * @param adp
 * @param dlg
 * @param app
 */
abstract class Ctrl(var adp: Adp)(using dlg: Dlg, app: App) extends _Ctrl:
  def ariaRole:AriaRole
  if adp == null then adp = wrap(_.getByRole(ariaRole))

  final def locs = adp(this)(app.pg)
  final def loc = locs.nth(idx)

  override def clickImpl() = {
    loc.click()
  }

  override def setImpl(value:Any) = {
    println(loc)
    loc.fill(value.toString)
    loc.press("Tab")
  }

abstract class Data(adp: Adp)(using dlg: Dlg, app: App) extends Ctrl(adp) with _Data

abstract class Action(adp: Adp)(using dlg: Dlg, app: App) extends Ctrl(adp) with _Action

class Txt(adp: Adp)(using dlg: Dlg, app: App) extends Data(adp) with _Txt {
  def ariaRole = AriaRole.TEXTBOX
}

class SpinBtn(adp: Adp)(using dlg: Dlg, app: App) extends Data(adp) with _SpinBtn {
  def ariaRole = AriaRole.SPINBUTTON
  override def setImpl(value:Any) = {
    val l =  loc
    println(l)
    l.fill(value.toString)
    l.press("Tab")
  }
}


@TBD
class Btn(adp: Adp)(using dlg: Dlg, app: App) extends Action(adp) with _Btn:
  def ariaRole = AriaRole.BUTTON

class InToBtn(adp: Adp)(using dlg: Dlg, app: App) extends Btn(adp) with _InToBtn
class RetBtn(adp: Adp)(using dlg: Dlg, app: App) extends Btn(adp) with _RetBtn
class BackBtn(adp: Adp)(using dlg: Dlg, app: App) extends Btn(adp) with _BackBtn
class NextBtn(adp: Adp)(using dlg: Dlg, app: App) extends Btn(adp) with _NextBtn

trait MixIn(using app: App) extends _MixIn:
  self: Dlg =>

trait OkCancel(using app: App) extends _OkCancel:
  self: Dlg =>

trait CancelNextPrevious(using app: App) extends Dlg with PreviousNextCancel:
  self: Dlg =>

