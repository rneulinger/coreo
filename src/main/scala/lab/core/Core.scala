package lab.core

import lab.*
import lab.utils.membersOfSubtype

/** Language for code generation. C#, Python...
 * default is C# */
class Lang:
end Lang

/** Technology, eg Playwright, Selenium, Ranorex, QfTest
 * default is Playwright */
sealed abstract class Tech:
end Tech

/** Playwright */
class PW extends Tech

/** Selenium */
class SE extends Tech

/** Qf-Test */
class QF extends Tech


/** Locator for a given technology */
class Loc:
end Loc

type UiId = String | Null

trait _Obj {
  def validate()={}
}
/** Application, a bunch of dialogs */
class _App extends _Obj:
  def methodApp() = println("Bingo from app")
  def goto(dest:String="/") ={
  }
/** a bunch of controls */
trait _Dlg(using app: _App) extends _Obj with IsTarget:
  def ctrls = membersOfSubtype(this, classOf[_Ctrl])

  def datas = membersOfSubtype(this, classOf[_Data])

  def actions = membersOfSubtype(this, classOf[_Data])

  def notify(ctrl: _Ctrl): Unit = {}

  def BTN(id: UiId = null): _Btn

  def TXT(id: UiId = null): _Txt

  //def adopt( ctrl:Ctrl) = {}
  def methodDlg() = {
    app.methodApp()
  }

/** Control within a dialog */
trait _Ctrl(using dlg: _Dlg, app: _App) extends _Obj{
  dlg.notify(this)

  def click(): _Dlg

  def set(value: Any): _Dlg

  def get(): String
}

trait _Data extends _Ctrl

trait _Txt extends _Data

trait _Action extends _Ctrl:
  private var target: Option[_Ctrl] = None

  def resetTarget(): Unit = ???

  def setTarget(ctrl: _Ctrl): Unit = ???

  def getTarget: Option[_Ctrl] = ???


trait _Btn extends _Action

trait MixIn(using app: _App):
  self: _Dlg =>

trait _OkCancel(using app: _App):
  self: _Dlg =>
  val Ok = BTN()
  val Cancel = BTN()

trait _Commit(using app: _App):
  self: _Dlg =>
  @Return
  val Close = BTN()
  val Msg = TXT()

trait Crud:
  def create(): _Action

  def read(): _Action

  def update(): _Action

  def delete(): _Action

