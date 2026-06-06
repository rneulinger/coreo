package coreo


object MyLab:

  /** Language for code generation. C#, Python...
   * default is C#*/
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

  /** Application, a bunch of dialogs*/
  class App:
    def methodApp() = println( "Bingo from app")
  
  /** a bunch of controls */
  trait Dlg(using app:App):
    def ctrls = membersOfSubtype(this, classOf[Ctrl])
    def datas = membersOfSubtype(this, classOf[Data])
    def actions = membersOfSubtype(this, classOf[Data])
    def BTN(id:String=null):Btn
    def TXT(id:String=null):Txt
    //def adopt( ctrl:Ctrl) = {}
    def methodDlg() ={
      app.methodApp()
    }

  
  /** Control within a dialog */
  trait Ctrl( using dlg:Dlg, app:App) {
    dlg.actions
    app.methodApp()
    def click():Dlg
  }
  trait Data extends Ctrl
  trait Action extends Ctrl
  trait Txt extends Data
  trait Btn extends Action
  trait OkCancel(using app:App):
    self: Dlg =>
    def Ok:Btn
    def Cancel :Btn

  trait Commit( using app:App) :
    val Close = Btn
    val Msg = Txt
    
  class DlgPw( using app:AppPw) extends Dlg:
    def BTN(id:String=null) = BtnPw(using this)
    def TXT(id:String=null) = TxtPw(using this)

  class CtrlPw( using dlg:DlgPw, appPw: AppPw) extends Ctrl:
    def click()=dlg
    
  class DataPw(using dlg: DlgPw, appPw: AppPw) extends CtrlPw with Data
  class ActionPw(using dlg: DlgPw, appPw: AppPw) extends CtrlPw with Action
  class TxtPw(using dlg: DlgPw, appPw: AppPw) extends DataPw with Txt
  class BtnPw(using dlg: DlgPw, appPw: AppPw) extends ActionPw with Btn
  trait OkCancelPw(using app: PwApp):
    self: DlgPw =>
    val Ok = BTN()
    val Cancel = BTN()
  
  trait MixinPw(using app:AppPw):
    self: DlgPw =>
    val Next = BTN()
    val Prev = BTN()

  class CommitPw(using app:AppPw) extends Commit
  
  class MyDialogPw(using app:AppPw)  extends DlgPw with MixinPw {
//    val Ok = BTN()
    given dlg:MyDialogPw = this

    def foo() = {
      app.methodApp()
    }
  }

  class AppPw extends App:
    def dlgs = membersOfSubtype(this, classOf[Dlg])




  class MyApp extends AppPw:
    val myDialog = MyDialogPw()
    val commit = CommitPw()
    given app:MyApp = this

  @main
  def startApp() = {
    val xxx = App()
    val app = MyApp()
    println( app.dlgs.distinct )
    println( app.myDialog.ctrls.distinct )
    val b = app.myDialog.Next
    app.commit.Close
  }


end MyLab


def membersOfSubtype[A, T](a: A, target: Class[T]): List[(String, T)] =
  val cls = a.getClass

  val fields =
    cls.getFields.toList ++ cls.getDeclaredFields.toList
  val methods =
    cls.getMethods.toList ++ cls.getDeclaredMethods.toList

  val fieldMatches =
    fields.collect {
      case f if target.isAssignableFrom(f.getType) =>
        f.setAccessible(true)
        f.getName -> f.get(a).asInstanceOf[T]
    }

  val methodMatches =
    methods.collect {
      case m if m.getParameterCount == 0 &&
        target.isAssignableFrom(m.getReturnType) =>
        m.setAccessible(true)
        m.getName -> m.invoke(a).asInstanceOf[T]
    }

  fieldMatches// ++ methodMatches


object usage:
  class A {
    val s: String = "hello"
    private val hidden: String = "secret"

    def number: Int = 42
  }

  class B extends A {
    def extra: String = "more"
  }

  val b = B()

  val strings = membersOfSubtype(b, classOf[String])
  @main
  def test()= {
    println(strings)
  }


object fieldOfExactType:
  def fieldsOfExactType[A, T](a: A, target: Class[T]): List[(String, T)] =
    val cls = a.getClass

    // includes inherited public fields + declared fields
    val fields =
      cls.getFields.toList ++ cls.getDeclaredFields.toList

    fields.collect {
      case f if f.getType == target =>
        f.setAccessible(true)
        f.getName -> f.get(a).asInstanceOf[T]
    }

  class Base:
    val base: String = "base"

  class Child extends Base:
    val child: String = "child"
    val number: Int = 42

  val c = Child()

  val strings = fieldsOfExactType(c, classOf[String])

  @main
  def usage2() = println(strings)