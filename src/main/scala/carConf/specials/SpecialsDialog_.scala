package carConf.specials

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*


// tag::fields[]
class SpecialsDialog_[+A <: App] (using own: App) extends Dlg {


  val ModelName = TXT(_.locator("#SpecialsName99_input"))

  val Price = TXT( _.locator("#SpecialsPrice99_input"))
  
  val Description = TXT(_.locator("#SpecialsDialogArea99"))

  val Accessories = TBL( _.locator("#AccessoryTable99"))

  val Specials = TAB(_.locator("#SpecialsTable"))

  // end::fields[]
  given ref: SpecialsDialog_[A] = this

}

object SpecialsDialog_ {
  
}