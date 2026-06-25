package lab.core

import coreo.{Return, TBD}
import lab.core.Interfaces.*
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
abstract class _App extends App with _Obj:
unify  /**
   * stack for goTo / goBack
   */
  private final val goHistory = scala.collection.mutable.Stack[_Dlg]()
  /**
   * stack for nextTo / backTo
   */
  private final val toHistory = scala.collection.mutable.Stack[_Dlg]()
  /**
   * stack for inTo / leave
   */
  private final val inHistory = scala.collection.mutable.Stack[_Dlg]()

  final def dlgMembers = collectMembersOfType(this, classOf[_Dlg])
  final def gotoMembers = dlgMembers

  final def findDialogs[T <: _Dlg](dlg: Class[T] | String): List[Dlg] = ???

  final def findDialog[T <: _Dlg](dlg: Class[T] | String): Dlg = ???

  final def goTo[T <: _Dlg](dlg:Class[T]|String="/") = ???
  final def goBack() = ???
  final def nextTo[T <: _Dlg](dlg: Class[T]|String) = ???
  final def backTo() = ???
  final def inTo[T <: _Dlg](dlg: Class[T]|String) = ???
  final def leave() = ???
  def Unknown:_Dlg
  def activeDlg:_Dlg = act

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
trait _Dlg(using app: _App) extends Dlg with _Obj:
  def act = app.act
  final def myApp = app
  final def activeDlg = app.act

  override def parentAnnotations: List[Annotation] = app.annotationsForObj(this);

  def ctrlMembers = collectMembersOfType(this, classOf[_Ctrl])

  def dataMembers = collectMembersOfType(this, classOf[_Data])

  def actionMembers = collectMembersOfType(this, classOf[_Action])

  def notify(ctrl: _Ctrl): Unit = {}

  def TXT(id: String = ""): _Txt

  def BTN(id:String=""): _Btn

  def RET(id:String=""): _Btn & RetBtn

  def SUB(id:String=""): _Btn & InToBtn

  def BAK(id:String=""): _Btn & BackBtn

  def NXT(id:String=""): _Btn & NextBtn

/** Control within a dialog */
trait _Ctrl(using dlg: _Dlg, app: _App) extends _Obj with Ctrl :
  dlg.notify(this)

  override def parentAnnotations: List[Annotation] = dlg.annotationsForObj(this)

  final def myApp = app
  final def activeDlg = app.act
  final def myDlg = dlg

  def click(): _Dlg

  def set(value: Any): _Dlg

  def get(): String
  def expect( prop:Prop*): Unit = ???
  def expectOneOf( prop:Prop*): Unit = ???
  def get(prop:Prop):Boolean = ???


trait _Data extends _Ctrl  with Data

trait _Txt extends _Data with Txt

trait _Action extends _Ctrl with Action:
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

trait _Btn extends _Action with Btn

trait _InToBtn extends _Btn with InToBtn

trait _RetBtn extends _Btn with RetBtn

trait _NextBtn extends _Btn with NextBtn

trait _BackBtn extends _Btn with BackBtn

