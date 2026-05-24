package carConf.ui.accessories

import coreo.*
import coreo.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

// tag::fields[]
class AccessoriesDialog_(using own: AnyApp) extends Dlg {


  val AccessoryName = TXT()

  val ID = TXT()

  val Price = TXT()

  // end::fields[]
  given ref: AccessoriesDialog_ = this
}

object AccessoriesDialog_ {}