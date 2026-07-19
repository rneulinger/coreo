package lab.core

import coreo.{Return, TBD}
import lab.core.Interfaces.*
import lab.utils.collectMembersOfType

import java.lang.annotation.Annotation
import scala.collection.mutable
import scala.language.postfixOps
import scala.collection.mutable.Stack
import scala.compiletime.uninitialized

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

trait _Obj extends Interfaces.Obj with Logging:

//  final def GoAnnotations()= getClass.getAnnotations.collect { case a: Ui => a.value }
//  final def ToAnnotations() = getClass.getAnnotations.collect { case a: To => a.dest }
//  final def UiAnnotations() = getClass.getAnnotations.collect { case a: Ui => a.value }
//  final def TbdAnnotations() = getClass.getAnnotations.collect { case a: Tbd => a }
//  final def ReturnAnnotations() = getClass.getAnnotations.collect { case a: Ret => a }


  def validate() = {
    // todo implement validations
  }

/** Application, a bunch of dialogs */
abstract class _App extends App with _Obj with LogSimple:
  final override def click(ctrl: String): Unit = activeDlg.click(ctrl)
  final override def set(ctrl: String, value:Any): Unit = activeDlg.set(ctrl, value)
  final override def expect(ctrl: String, value:Any): Unit = activeDlg.expect(ctrl, value)
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
  final def gotoDlgs = allDlgs.filter(_._2.goAnnotations.nonEmpty)

  /**
   * all dialogs of a certain type.
   * can be usefull to validate
   * @param dlg
   * @tparam T
   * @return
   */
  override def findDlgs[T <: Dlg](dlg: Class[T] | String | Dlg): List[(String,Dlg)] =
    dlg match
      case d:Class[T] => allDlgs.filter(x => d.isInstance(x._2))
      case d:Dlg => allDlgs.filter(x => d == x._2)
      case str:String => throw NotImplementedError("find dialog by string")

  /**
   * find a dialog by its type.
   * todo search by instance
   * @param dlg
   * @tparam T
   * @return
   */
  override final def findDlg[T <: Dlg](dlg: Class[T] | String | Dlg ): (String,Dlg) =
    val hits = findDlgs(dlg)
    hits match
      case head::Nil => head
      case Nil => throw Exception(s"could not find dialog of type $dlg")
      case _ => throw Exception(s"multiple dialogs of type $dlg $hits")

  override final def goTo[T <: Dlg](dest: Class[T] | Dlg | String = "/"): Unit =
    dest match {
      case url: String =>
        val gotos = gotoDlgs
        act = Unknown // todo try to find dialog
        navigate(url)

      case cla: Class[Dlg] =>
        val dlg = findDlg(cla)
        goTo( dlg._2)

      case dlg:Dlg =>
        val gotos = dlg.goAnnotations.map(_.value()).distinct
        if gotos.isEmpty then throw Exception(s"goto annotation missing for ${dlg}")
        if gotos.tail.nonEmpty then throw Exception(s"multiple goto annotation for ${dlg} ${gotos}")
        info(s"goto: ${gotos.head} ${dlg}")
        navigate(gotos.head)
        act = dlg
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
  final def instanceName =
    def hits = app.findDlgs(this)
    if hits.isEmpty then ""
    else hits.head._1

  final def activeDlg = app.activeDlg

  final override def click(ctrl: String): Unit = findCtrl(ctrl)._2.click()
  final override def set(ctrl: String, value:Any): Unit = findCtrl(ctrl)._2.set(value)
  final override def expect(ctrl: String, value:Any): Unit = findCtrl(ctrl)._2.expect(value)

  override def logger: Logging = app
  override def parentAnnotations: List[Annotation] = app.annotationsForObj(this);

  def allCtrls = collectMembersOfType(this, classOf[_Ctrl])

  def allDatas = collectMembersOfType(this, classOf[_Data])

  def allActions = collectMembersOfType(this, classOf[_Action])

  final override def findCtrls[T <: Ctrl](ctrl: Class[T] | Ctrl | String): List[(String, Ctrl)] =
    ctrl match
      case c: Class[T] => allCtrls.filter(x => c.isInstance(x._2))
      case str: String => allCtrls.filter(x => x._1 == str)
      case c: Ctrl => allCtrls.filter(x => c == x._2)

  final override def findCtrl[T <: Ctrl](ctrl: Class[T] | Ctrl |String ): (String,Ctrl) =
    val hits = findCtrls(ctrl)
    hits match
      case head::Nil => head
      case Nil => throw Exception(s"could not find dialog of type $ctrl")
      case _ => throw Exception(s"multiple dialogs of type $ctrl $hits")

  def notify(ctrl: _Ctrl): Unit = {}

  def TXT(id: String = ""): _Txt

  def SpinBTN(id: String = ""): _SpinBtn
  
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

  final def instanceName =
    def hits = dlg.findCtrls(this)
    if hits.isEmpty then ""
    else hits.head._1

  def myApp = app
  def activeDlg = app.activeDlg
  final def myDlg = dlg

  protected def clickImpl():Unit
  def click(): Unit =
    info(s"$instanceName.click")
    clickImpl()

  protected def setImpl(value: Any):Unit
  def set(value: Any): Unit =
    info(s"$instanceName.set $value")
    setImpl(value)

  protected def getImpl():Any
  def get(): Unit = 
    info(s"$instanceName.get")
    getImpl()
  

  final def expect( value:Any): Unit = 
    value match
      case prop:Prop => expectOneOf(prop)
    
  def expectOneOf( prop:Prop*): Unit = ???
  def get(prop:Prop):Boolean = ???


trait _Data extends _Ctrl  with Data

trait _Txt extends _Data with Txt

trait _SpinBtn extends _Data with SpinBtn

trait _Action extends _Ctrl with Action

trait _Btn extends _Action with Btn

trait _InToBtn extends _Btn with InToBtn

trait _RetBtn extends _Btn with RetBtn

trait _NextBtn extends _Btn with NextBtn

trait _BackBtn extends _Btn with BackBtn

object NoneApp extends _App:

  override def navigate(path: String): Unit = ???

  override def Unknown: _Dlg = ???

  given app: _App = this

  override def instanceName: String = ""

object Env extends Interfaces.Env {
  /**
   * all registered applications
   * it is possible to have an application registered with more than one name
   *
   * @return
   */
  var apps = Map[String, App]()
  use (NoneApp, "NoneApp")
  private var actApp:App = uninitialized
  use(NoneApp, "NoneApp")

  override def activeApp: App = actApp

  override def add(app: App, name: String): App = {
    if ( ! apps.contains(name) ){  // new app
      apps = apps + (name -> app)
      return app
    }
    if ( apps(name) == app){ // already defined
      return app
    }
    throw Exception(s"{name} is already in use")
  }

  override def use(app: App, name: String):App = {
    add( app, name)
    use( name )
  }
  override def use(name: String): App = {
    actApp = apps(name)
    actApp
  }

  /**
   * return whether an App with a given Name exists
   *
   * @param name of application to finad
   * @return
   */
  override def exists(name: String): Boolean = apps.contains(name)
}

object NoneDlg extends Interfaces.Dlg {
  /**
   * Helpers to create default Ctrls and By ids
   *
   * @param id unique id of this control if defined, default is "" which means no id
   * @return
   */
  override def TXT(id: String): Txt = ???

  override def BTN(id: String): Btn = ???

  override def SUB(id: String): InToBtn = ???

  override def RET(id: String): RetBtn = ???

  override def NXT(id: String): NextBtn = ???

  override def BAK(id: String): BackBtn = ???

  override def SpinBTN(id: String): SpinBtn = ???

  /**
   * all controls of this dialog
   *
   * @return
   */
  override def allCtrls: List[(String, Ctrl)] = ???

  /**
   * all data controls
   *
   * @return
   */
  override def allDatas: List[(String, Data)] = ???

  /**
   * all action controls
   *
   * @return
   */
  override def allActions: List[(String, Action)] = ???

  override def findCtrls[T <: Ctrl](ctrl: Class[T] | Ctrl | String): List[(String, Ctrl)] = ???

  override def findCtrl[T <: Ctrl](ctrl: Class[T] | Ctrl | String): (String, Ctrl) = ???

  /**
   * clicks a control by a given name
   *
   * @param ctrl name of Ctrl
   */
  override def click(ctrl: String): Unit = ???

  /**
   * sets the value of a control by a given name
   *
   * @param ctrl name of Ctrl
   */
  override def set(ctrl: String, value: Any): Unit = ???

  /**
   * expects the value of a control by a given name
   *
   * @param ctrl name of Ctrl
   */
  override def expect(ctrl: String, valueOf: Any): Unit = ???

  /**
   *
   * @return the application to which the object belongs.
   */
  override def myApp: App = ???

  override def instanceName: String = ???

  /**
   * @return the currently active dialogue.
   */
  override def activeDlg: Dlg = ???

  /**
   * performs validation on the current object.
   * there will be validations in the future
   */
  override def validate(): Unit = ???
}
