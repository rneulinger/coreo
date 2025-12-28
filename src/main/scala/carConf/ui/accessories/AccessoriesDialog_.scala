package carConf.ui.accessories

import coreo.*
import coreo.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class AccessoriesDialog_(own: CanOwn) extends DLG(own) {

  // tag::fields[]
  given ref: OWNER[AccessoriesDialog_] = OWNER(this)

  val AccessoryName = TXT("Accessory name")

  val ID = TXT("")

  val Price = TXT("")

  // end::fields[]
}

object AccessoriesDialog_ {}