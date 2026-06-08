package lab.pw

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

import lab.core.{_Commit, _OkCancel}
import lab.utils.*

class Something(using app: App) extends Dlg with _OkCancel

class CommitPw(using app: App) extends Dlg with _Commit

class MyDialog(using app: App) extends Dlg with CancelNextPrevious {
  //    val Ok = BTN()
  given dlg: MyDialog = this

  val FirstName = BTN(_.locator(""))
  val Gender = Btn(wrap(_.locator("")))

  val Name = TXT()

  def foo() = {
    app.methodApp()
    Name.click()
    Next.click()
    Next.set("jjj")
    Previous.click()
  }

}


class MyAppPw extends App:
  val myDialog = MyDialog()
  //val commit = CommitPw()
  val commit = new Dlg with _Commit() {}

  given app: MyAppPw = this

@main
def startApp() = {
  val app = MyAppPw()
  println(app.dlgs.distinct)
  println(app.myDialog.ctrls.distinct)
  val b = app.myDialog.Next.click()

  app.myDialog.use: x =>
    x.Name.click()
    x.Next.click()
    x.Previous.set(1)


  app.commit.use: x =>
    x.Close.click()


  app.commit.Close.click()
  app.myDialog.Name
}

