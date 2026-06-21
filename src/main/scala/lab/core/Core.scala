package lab.core

import coreo.{Return, TBD}
import lab.*
import lab.utils.collectMembersOfType

import java.lang.annotation.Annotation
import scala.language.postfixOps

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
  case Readonly
  case Present
  case Absent

type UiId = String | Null

trait _Obj extends Interfaces.Obj {

//  final def GoAnnotations()= getClass.getAnnotations.collect { case a: Ui => a.value }
//  final def ToAnnotations() = getClass.getAnnotations.collect { case a: To => a.dest }
//  final def UiAnnotations() = getClass.getAnnotations.collect { case a: Ui => a.value }
//  final def TbdAnnotations() = getClass.getAnnotations.collect { case a: Tbd => a }
//  final def ReturnAnnotations() = getClass.getAnnotations.collect { case a: Ret => a }


  def validate() = {
    // todo implement validations
  }
}
/** Application, a bunch of dialogs */
abstract class _App extends Interfaces.App with _Obj:
  final def dlgMembers = collectMembersOfType(this, classOf[_Dlg])
  final def gotoMembers = dlgMembers
  final def goTo(path:String="/") = ???
  final def goTo[T <: _Dlg](dlg:Class[T]) = ???
  final def onTo(dlgName:String) = ???
  final def onTo[T <: _Dlg](dlg: Class[T]) = ???
  def Unknown:_Dlg
  def actDialog = act

  /**
   * last dialogs -> pushed by to-button, popped by back-button
   * @return
   */
  def lastDlgs = scala.collection.mutable.Stack[_Dlg]()
  /**
   * gosub dialogs -> pushed by sub-Button, popped by return-button
   * @return
   */
  def subDlgs = scala.collection.mutable.Stack[_Dlg]()
  def push(dlg: Interfaces.Dlg): Unit = ???

  def myApp = this
  def act: _Dlg = ???


  private def push(dlg: _Dlg): Unit = ???

  private def pop(): _Dlg = ???


/** a bunch of controls */
trait _Dlg(using app: _App) extends Interfaces.Dlg with _Obj:
  def act = app.act
  final def myApp = app
  final def actDialog = app.act

  override def parentAnnotations: List[Annotation] = app.annotationsForObj(this);

  def ctrlMembers = collectMembersOfType(this, classOf[_Ctrl])

  def dataMembers = collectMembersOfType(this, classOf[_Data])

  def actionMembers = collectMembersOfType(this, classOf[_Action])

  def notify(ctrl: _Ctrl): Unit = {}

  def TXT(id: UiId = null): _Txt

  def BTN(id: UiId = null): _Btn

  def RET(id: UiId = null): _Btn & Interfaces.Ret

  def SUB(id: UiId = null): _Btn & Interfaces.Sub

/** Control within a dialog */
trait _Ctrl(using dlg: _Dlg, app: _App) extends Interfaces.Ctrl with _Obj:
  dlg.notify(this)

  override def parentAnnotations: List[Annotation] = dlg.annotationsForObj(this)

  final def myApp = app
  final def actDialog = app.act
  final def myDlg = dlg

  def click(): _Dlg

  def set(value: Any): _Dlg

  def get(): String
  def expect( prop:Prop*): Unit = ???
  def expectOneOf( prop:Prop*): Unit = ???
  def get(prop:Prop):Boolean = ???


trait _Data extends Interfaces.Data with _Ctrl

trait _Txt extends _Data

trait _Action extends Interfaces.Action with _Ctrl:
  private var target: Option[_Dlg] = None

  def resetTarget(): Unit = target = None

  def setTarget(dlg: _Dlg): Unit = target = Some(dlg)

  def getTarget: Option[_Dlg] = {
    if( target.isDefined )  {
      target
    } else {
//      val returnOpt =
//        getClass.getAnnotations
//          .collectFirst { case a: Ret => a }
//
//      println(returnOpt) // Some("hello")
//
//      val overloadOpt =
//        getClass.getAnnotations
//          .collectFirst { case a: Tbd => a }
//
//      println(overloadOpt) // Some("hello")
//
//      val onTOOpt =
//        getClass.getAnnotations
//          .collectFirst { case a: To => a }
//
//      println(onTOOpt.map(_.dest)) // Some("hello")
      None
    }
  }

trait _Btn extends _Action with Interfaces.Action

trait _SubBtn extends _Btn with Interfaces.Sub

trait _RetBtn extends _Btn with Interfaces.Ret

trait _ToBtn extends _Btn with Interfaces.To

trait _BackBtn extends _Btn with Interfaces.Back

