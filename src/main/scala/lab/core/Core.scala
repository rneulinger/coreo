package lab.core

import coreo.{Return, TBD}
import lab.core.Interfaces.*
import lab.utils.collectMembersOfType

import java.lang.annotation.Annotation
import scala.collection.mutable
import scala.language.postfixOps
import scala.collection.mutable.Stack

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

trait _Obj extends Interfaces.Obj with Logging {

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
abstract class _App extends App with _Obj with LogSimple:
  /**
   * stack for goTo / goBack.
   * the stack is populated on got with the active dialog when it has a goto annotation
   */
  private final val goHistory = mutable.Stack[_Dlg]()
  /**
   * stack for inTo / leave
   */
  private final val inHistory = mutable.Stack[(_Dlg, mutable.Stack[Dlg])]()

  /**
   * stack for nextTo / backTo
   */
  private final val toHistory = mutable.Stack[_Dlg]()

  final def allDlgs:List[(String,Dlg)] = collectMembersOfType(this, classOf[_Dlg])
  final def gotoMembers = ???

  /**
   * all dialogs of a certain type.
   * can be usefull to validate
   * @param dlg
   * @tparam T
   * @return
   */
  override def findDlgs[T <: Dlg](dlg: Class[T] | String ): List[(String,Dlg)] = {
    dlg match{
      case dlg:Class[T] => allDlgs.filter(x => dlg.isInstance(x._2))
      case str:String => throw NotImplementedError("find dialog by string")
    }

  }

  /**
   * find a dialog by its type.
   * todo search by instance
   * @param dlg
   * @tparam T
   * @return
   */
  override final def findDlg[T <: Dlg](dlg: Class[T] | String ): (String,Dlg) = {
    val hits = findDlgs(dlg)
    hits match{
      case head::Nil => head
      case Nil => throw Exception(s"could not find dialog of type $dlg")
      case _ => throw Exception(s"multiple dialogs of type $dlg $hits")
    }
  }

  def goTo[T <: Dlg](dlg: Class[T] | String = "/"): Unit = {

    dlg match {
      case url: String =>
        navigate(url)
      case dlg: Class[_Dlg] =>
        val dlgs = myObjs.filter(_._2.isInstanceOf[Dlg])
        val hits = dlgs.filter(x => dlg.isInstance(x._2))
        hits.size match {
          case 1 =>
            val hit = hits.head
            val gotos = hit._2.goAnnotations.map(_.value()).distinct
            if gotos.isEmpty then throw Exception(s"goto annotation missing for ${hits}")
            if gotos.tail.nonEmpty then throw Exception(s"multiple goto annotation for ${hits} ${gotos}")
            info(s"goto: ${gotos.head} ${hit._2}")
            navigate(gotos.head)
            act = hits.head._2.asInstanceOf[Dlg]

          case 0 => throw new Exception(s"could not find a dialog of type ${dlg} ")
        }
    }
  }


  def navigate(path:String):Unit
  final def goBack() = ???
  def nextTo[T <: Dlg](dlg: Class[T]|String) = ???
  final def backTo() = ???
  def inTo[T <: Dlg](dlg: Class[T]|String) = ???
  final def leave() = ???
  def Unknown:_Dlg
  var act : Dlg = Unknown
  def activeDlg:_Dlg = act.asInstanceOf[_Dlg]

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


  private def push(dlg: _Dlg): Unit = ???

  private def pop(): _Dlg = ???


/** a bunch of controls */
trait _Dlg(using app: _App) extends Dlg with _Obj with LogDelegate:
  final def myApp = app
  final def activeDlg = app.activeDlg

  override def logger: Logging = app
  override def parentAnnotations: List[Annotation] = app.annotationsForObj(this);

  def allCtrls = collectMembersOfType(this, classOf[_Ctrl])

  def allDatas = collectMembersOfType(this, classOf[_Data])

  def allActions = collectMembersOfType(this, classOf[_Action])

  def notify(ctrl: _Ctrl): Unit = {}

  def TXT(id: String = ""): _Txt

  def BTN(id:String=""): _Btn

  def RET(id:String=""): _Btn & RetBtn

  def SUB(id:String=""): _Btn & InToBtn

  def BAK(id:String=""): _Btn & BackBtn

  def NXT(id:String=""): _Btn & NextBtn

/** Control within a dialog */
trait _Ctrl(using dlg: _Dlg, app: _App) extends _Obj with Ctrl with LogDelegate:
  dlg.notify(this)
  override def logger: Logging = dlg

  override def parentAnnotations: List[Annotation] = dlg.annotationsForObj(this)

  def myApp = app
  def activeDlg = app.activeDlg
  final def myDlg = dlg

  def click(): _Dlg

  def set(value: Any): _Dlg

  def get(): String
  def expect( prop:Prop*): Unit = ???
  def expectOneOf( prop:Prop*): Unit = ???
  def get(prop:Prop):Boolean = ???


trait _Data extends _Ctrl  with Data

trait _Txt extends _Data with Txt

trait _Action extends _Ctrl with Action

trait _Btn extends _Action with Btn

trait _InToBtn extends _Btn with InToBtn

trait _RetBtn extends _Btn with RetBtn

trait _NextBtn extends _Btn with NextBtn

trait _BackBtn extends _Btn with BackBtn

