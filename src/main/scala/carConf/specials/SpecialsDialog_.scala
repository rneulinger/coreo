package carConf.specials

import coreo.*

class SpecialsDialog_(own: CanOwn) extends DLG(own) {

  // tag::fields[]
  given ref: OWNER[SpecialsDialog_] = OWNER(this)

  val Specials = TAB("",
    _.locator("#SpecialsTable"))

  val ModelName = TXT("Model name",
    _.locator("#SpecialsName99_input"))

  val Price = TXT("",
    _.locator("#SpecialsPrice99_input"))

  val Description = TXT("",
    _.locator("#SpecialsDialogArea99"))

  val Accessories = TBL("",
    _.locator("#AccessoryTable99"))

  // end::fields[]

}

object SpecialsDialog_ {
  
}