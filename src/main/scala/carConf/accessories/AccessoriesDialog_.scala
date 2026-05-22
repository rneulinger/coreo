package carConf.accessories

import coreo.*
import coreo.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class AccessoriesDialog_(own: AnyApp) extends Dlg(own) {

  // tag::fields[]

  val AccessoryName = TXT()

  val ID = TXT()

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