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

/** Application, a bunch of dialogs */
class _App:
  def methodApp() = println("Bingo from app")

/** a bunch of controls */
trait _Dlg(using app: _App) extends Destination:
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
trait _Ctrl(using dlg: _Dlg, app: _App) {
  dlg.notify(this)
  dlg.actions
  app.methodApp()

  def click(): _Dlg

  def set(value: Any): _Dlg

  def get(): String
}

trait _Data extends _Ctrl

trait _Txt extends _Data

trait _Action extends _Ctrl

trait _Btn extends _Action

trait MixIn(using app: _App):
  self: _Dlg =>

trait _OkCancel(using app: _App):
  self: _Dlg =>
  val Ok = BTN()
  val Cancel = BTN()

trait _Commit(using app: _App):
  self: _Dlg =>
  val Close = BTN()
  val Msg = TXT()

trait Crud:
  def create(): _Action

  def read(): _Action

  def update(): _Action

  def delete(): _Action

trait _Whiz(left: _Whiz.Left, right: _Whiz.Right, mids: _Whiz.Mid*)

object _Whiz:
  trait Left:
    def cancel(): _Action

    def next(): _Action

  trait Right:

    def cancel(): _Action

    def ok(): _Action

    def previous(): _Action

  trait Mid:
    def cancel(): _Action

    def next(): _Action

    def previous(): _Action

trait _CancelNext(using app: _App) extends _Whiz.Right:
  self: _Dlg =>
  @To(dest = classOf[Overload])
  val Cancel = BTN()
  @To(dest = classOf[Overload])
  val Next = BTN()

  def cancel() = Cancel

  def next() = Next

trait _CancelNextPrevious extends _Whiz.Mid:
  self: _Dlg =>
  val Cancel = BTN()
  val Next = BTN()
  val Previous = BTN()

  def cancel() = Cancel

  def next() = Next

  def previous() = Previous

trait _OkCancelPrevious extends _Whiz.Left with _OkCancel:
  self: _Dlg =>
  val Previous = BTN()

  def cancel() = Cancel

  def previous() = Previous

  def ok() = Ok


