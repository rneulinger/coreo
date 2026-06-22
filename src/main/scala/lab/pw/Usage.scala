package lab.pw

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}
import lab.core.{_Commit, _Dlg, _OkCancel, _Txt}
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
    Name.click()
    Next.click()
    Next.set("jjj")
    Previous.click()
  }
}

trait _AnotherDialog extends _OkCancel {
  self: _Dlg =>
  val Info = TXT()
} 
class MyAppPw extends App:
  val myDialog = MyDialog()
  //val commit = CommitPw()
  val commit = new Dlg with _Commit() 
  val another = new Dlg with _AnotherDialog 
  given app: MyAppPw = this

@main
def startApp() = {
  val app = MyAppPw()
  println(app.dlgMembers.distinct)
  println(app.myDialog.ctrlMembers.distinct)
  val b = app.myDialog.Next.click()

  app.myDialog.use: x =>
    x.Name.click()
    x.Next.click()
    x.Previous.set(1)


  app.commit.use: x =>
    x.Yes.click()


  app.commit.No.click()
  app.myDialog.Name
}

