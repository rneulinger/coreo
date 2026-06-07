package lab

// playwright
class DlgPw(using app: AppPw) extends Dlg:

  def BTN(id: String = null): BtnPw = BtnPw(using this)

  def TXT(id: String = null): TxtPw = TxtPw(using this)

abstract class CtrlPw(using dlg: DlgPw, appPw: AppPw) extends Ctrl:
  def click() = dlg

  def set(value: Any) = dlg

  def get(): String = ""

abstract class DataPw(using dlg: DlgPw, appPw: AppPw) extends CtrlPw with Data

abstract class ActionPw(using dlg: DlgPw, appPw: AppPw) extends CtrlPw with Action

class TxtPw(using dlg: DlgPw, appPw: AppPw) extends DataPw with Txt

class BtnPw(using dlg: DlgPw, appPw: AppPw) extends ActionPw with Btn

trait MixInPw(using app: AppPw) extends MixIn:
  self: DlgPw =>

trait OkCancelPw extends MixInPw:
  self: DlgPw =>
  val Ok = BTN()
  val Cancel = BTN()

trait NextPrevPw(using app: AppPw):
  self: DlgPw =>
  val Next = BTN()
  val Prev = BTN()

class CommitPw(using app: AppPw) extends DlgPw with Commit

class MyDialogPw(using app: AppPw) extends DlgPw with NextPrevPw {
  //    val Ok = BTN()
  given dlg: MyDialogPw = this

  val Name = TXT()

  def foo() = {
    app.methodApp()
    Name.click()
    Next.click()
    Prev.click()
  }
}

class AppPw extends App:
  def dlgs = membersOfSubtype(this, classOf[Dlg])


class MyAppPw extends AppPw:
  val myDialog = MyDialogPw()
  //val commit = CommitPw()
  val commit = new DlgPw with Commit() {}

  given app: MyAppPw = this

@main
def startApp() = {
  val xxx = App()
  val app = MyAppPw()
  println(app.dlgs.distinct)
  println(app.myDialog.ctrls.distinct)
  val b = app.myDialog.Next.click()
  app.commit.Close.click()
  app.myDialog.Name
}

