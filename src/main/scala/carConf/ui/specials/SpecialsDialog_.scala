package carConf.ui.specials

import com.microsoft.playwright.Page
import coreo.*

// tag::fields[]
class SpecialsDialog_(own: AnyApp) extends Dlg(own) {

  given ref: SpecialsDialog_ = this

  val Specials = TAB( _.
    locator("#SpecialsTable"))

  val ModelName = TXT(_.
    locator("#SpecialsName99_input"))

  val Price = TXT( _.
    locator("#SpecialsPrice99_input"))

  val Description = TXT( _.
    locator("#SpecialsDialogArea99"))

  val Accessories = TBL( _.
    locator("#AccessoryTable99"))

  // end::fields[]

}

object SpecialsDialog_ {}