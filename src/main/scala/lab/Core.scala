package lab

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

/** Application, a bunch of dialogs */
class App:
  def methodApp() = println("Bingo from app")

/** a bunch of controls */
trait Dlg(using app: App):
  def ctrls = membersOfSubtype(this, classOf[Ctrl])

  def datas = membersOfSubtype(this, classOf[Data])

  def actions = membersOfSubtype(this, classOf[Data])

  def notify(ctrl: Ctrl): Unit = {}

  def BTN(id: String = null): Btn

  def TXT(id: String = null): Txt

  //def adopt( ctrl:Ctrl) = {}
  def methodDlg() = {
    app.methodApp()
  }


/** Control within a dialog */
trait Ctrl(using dlg: Dlg, app: App) {
  dlg.notify(this)
  dlg.actions
  app.methodApp()

  def click(): Dlg

  def set(value: Any): Dlg

  def get(): String
}

trait Data extends Ctrl

trait Action extends Ctrl

trait Txt extends Data

trait Btn extends Action

trait MixIn(using app: App):
  self: Dlg =>

trait OkCancel(using app: App):
  self: Dlg =>
  val Ok: Btn
  val Cancel: Btn

trait Commit(using app: App):
  self: Dlg =>
  val Close = BTN()
  val Msg = TXT()

trait Whiz(left: Whiz.Left, right: Whiz.Right, mids: Whiz.Mid*)

object Whiz:
  trait Left:
    def cancel(): Ctrl

    def next(): Ctrl

  trait Right:
    def cancel(): Ctrl

    def ok(): Ctrl

    def previous(): Ctrl

  trait Mid:
    def cancel(): Ctrl

    def next(): Ctrl

    def previous(): Ctrl

trait CancelNext(using app: App) extends Whiz.Right:
  self: Dlg =>
  val Cancel = BTN()
  val Next = BTN()

  def cancel() = Cancel

  def next() = Next

trait CancelNextPrevious extends Whiz.Mid:
  self: Dlg =>
  val Cancel = BTN()
  val Next = BTN()
  val Previous = BTN()

  def cancel() = Cancel

  def next() = Next

  def previous() = Previous

trait CancelOkPrevious extends Whiz.Left:
  self: Dlg =>
  val Ok = BTN()
  val Cancel = BTN()
  val Previous = BTN()

  def cancel() = Cancel

  def previous() = Previous

  def ok() = Ok


