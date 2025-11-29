package carConf.accessories

import coreo.*
import coreo.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class AccessoriesDialog(own:CanOwn ) extends FRM(own){

  // tag::fields[]
  given ref: Own[AccessoriesDialog] = Own(this)



  val `Accessory name` = TXT()
  def AccessoryName = `Accessory name` // alias
  val ID = TXT()
  val Price = TXT()

  val New = BTN()
  val Change = BTN()
  val Delete = BTN()
  val Ok = BTN()
  val Cancel = BTN()

  // end::fields[]
}