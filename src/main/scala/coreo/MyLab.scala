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
    def bingo() = println( "Bingo from app")
  end App

  /** a bunch of controls */
  trait Dlg(using app:App):
    def ctrls = membersOfSubtype(this, classOf[Ctrl])
    def adopt( ctrl:Ctrl) = {}
    def TXT():Ctrl
    def dddd() ={

    }

  /** Control within a dialog */
  trait Ctrl( using dlg:Dlg) {
    dlg.adopt(this)
  }
  trait Data extends Ctrl
  trait Action extends Ctrl
  trait Txt extends Data
  trait Btn extends Action

  trait MixinPw(using app:App) {
    self: DlgPw =>
    val Next = BTN()
    val Prev = BTN()
  }

  class MyDialog(using app:AppPw)  extends DlgPw with MixinPw {
    val Ok = BTN()
    given dlg:MyDialog = this

    def foo() = {
      app.bingo()
    }
  }

  class AppPw extends App

  class DlgPw( using app:AppPw) extends Dlg:
    def CTRL() = CtrlPw(using this)
    def BTN() = CtrlPw(using this)
    def TXT() = CtrlPw(using this)

  class CtrlPw( using val dlg:DlgPw) extends Ctrl


  class MyApp extends AppPw:
    val myDialog = MyDialog()

    given app:MyApp = this

  @main
  def startApp() = {
    val app = MyApp()
    println( app.myDialog.ctrls.distinct )
  }


end MyLab


import scala.deriving.Mirror
import scala.compiletime.{erasedValue, summonInline, constValueTuple}

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