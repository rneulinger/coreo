package lab.core

import lab.*
import lab.utils.collectMembersOfType

/** Language for code generation. C#, Python...
 * default is C# */
enum Lang:
  case Python
  case CSharp
  case TypeScript

enum Prop:
  case Enabled
  case Disabled
  case Hidden
  case Visible
  case Editable

type UiId = String | Null

trait _Obj {
  def validate()={}
}
/** Application, a bunch of dialogs */
class _App extends _Obj:
  def dlgMembers = collectMembersOfType(this, classOf[_Dlg])
  def methodApp() = println("Bingo from app")
  def goto(dest:String="/") ={
  }
/** a bunch of controls */
trait _Dlg(using app: _App) extends _Obj with IsTarget:
  def ctrlMembers = collectMembersOfType(this, classOf[_Ctrl])

  def dataMembers = collectMembersOfType(this, classOf[_Data])

  def actionMembers = collectMembersOfType(this, classOf[_Action])

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
  def expect( prop:Prop*): Unit = ???
  def expectOneOf( prop:Prop*): Unit = ???
  def get(prop:Prop):Boolean = ???
}

trait _Data extends _Ctrl

trait _Txt extends _Data

trait _Action extends _Ctrl:
  private var target: Option[_Ctrl] = None

  def resetTarget(): Unit = ???

  def setTarget(ctrl: _Ctrl): Unit = ???

  def getTarget: Option[_Ctrl] = ???


trait _Btn extends _Action

