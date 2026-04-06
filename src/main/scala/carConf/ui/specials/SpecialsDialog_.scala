package carConf.ui.specials

import com.microsoft.playwright.Page
import coreo.*

class SpecialsDialog_(own: CanOwn) extends DLG(own) {

  // tag::fields[]
  given ref: OWNER[SpecialsDialog_] = OWNER(this)

  val Specials = TAB("", (p:Page) => p.
    locator("#SpecialsTable"))

  val ModelName = TXT("Model name", (p:Page) => p.
    locator("#SpecialsName99_input"))

  val Price = TXT("", (p:Page) => p.
    locator("#SpecialsPrice99_input"))

  val Description = TXT("", (p:Page) => p.
    locator("#SpecialsDialogArea99"))

  val Accessories = TBL("", (p:Page) => p.
    locator("#AccessoryTable99"))

  // end::fields[]

}

object SpecialsDialog_ {}