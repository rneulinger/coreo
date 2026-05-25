package carConf.accessories

import coreo.*
import coreo.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

// tag::fields[]
class AccessoriesDialog_[A](using own: AnyApp) extends Dlg[A] {

  val AccessoryName = TXT()

  val Id = TXT()

  val Price = TXT()

  val New = BTN()

  val Change = BTN()
  //Change.target = "MyDialog"

  val Delete = BTN()

  val Ok = BTN()

  val Cancel = BTN()

  // end::fields[]
  given ref: AccessoriesDialog_ = this
}