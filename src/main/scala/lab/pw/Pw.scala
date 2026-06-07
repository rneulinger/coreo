package lab.pw

import lab.core.*
import lab.*

class App extends _App:
  def dlgs = membersOfSubtype(this, classOf[_Dlg])

// playwright
class Dlg(using app: App) extends _Dlg:

  def BTN(id: String = null): Btn = Btn(using this)

  def TXT(id: String = null): Txt = Txt(using this)

abstract class Ctrl(using dlg: Dlg, app: App) extends _Ctrl:
  def click() = dlg

  def set(value: Any) = dlg

  def get(): String = ""

abstract class Data(using dlg: Dlg, app: App) extends Ctrl with _Data

abstract class Action(using dlg: Dlg, app: App) extends Ctrl with _Action

class Txt(using dlg: Dlg, app: App) extends Data with _Txt

class Btn(using dlg: Dlg, app: App) extends Action with _Btn

trait MixInPw(using app: App) extends MixIn:
  self: Dlg =>

trait OkCancel(using app: App) extends _OkCancel:
  self: Dlg =>

trait CancelNextPrevious(using app: App) extends Dlg with _CancelNextPrevious:
  self: Dlg =>

class Something(using app: App) extends Dlg with _OkCancel
class CommitPw(using app: App) extends Dlg with _Commit

class MyDialogPw(using app: App) extends Dlg with CancelNextPrevious {
  //    val Ok = BTN()
  given dlg: MyDialogPw = this

  val Name = TXT()

  def foo() = {
    app.methodApp()
    Name.click()
    Next.click()
    Previous.click()
  }

}



class MyAppPw extends App:
  val myDialog = MyDialogPw()
  //val commit = CommitPw()
  val commit = new Dlg with _Commit() {}

  given app: MyAppPw = this

@main
def startApp() = {
  val xxx = _App()
  val app = MyAppPw()
  println(app.dlgs.distinct)
  println(app.myDialog.ctrls.distinct)
  val b = app.myDialog.Next.click()

  app.myDialog.use: x =>
    x.Name.click()
    x.Next.click()
    x.Previous.set(1)
  

  app.commit.use:x =>
    x.Close.click()


  app.commit.Close.click()
  app.myDialog.Name
}

