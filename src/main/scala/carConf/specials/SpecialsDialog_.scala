package carConf.specials

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*


class SpecialsDialog_(own: CanOwn) extends DLG(own) {

  // tag::fields[]
  given ref: OWNER[SpecialsDialog_] = OWNER(this)

  val ModelName = TXT("Model name", (p:Page) => p.
    locator("#SpecialsName99_input"))

  val Price = TXT("", (p:Page) => p. 
    locator("#SpecialsPrice99_input"))
  
  val Description = TXT("", (p:Page) => p.
    locator("#SpecialsDialogArea99"))

  val Accessories = TBL("", (p:Page) => p.
    locator("#AccessoryTable99"))

  val Specials = TAB("", (p:Page) => p.
    locator("#SpecialsTable"))

  // end::fields[]

}

object SpecialsDialog_ {
  
}