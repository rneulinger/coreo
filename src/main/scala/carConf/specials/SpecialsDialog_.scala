package carConf.specials

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*


// tag::fields[]
class SpecialsDialog_[A] (using own: AnyApp) extends Dlg[A] {


  val ModelName = TXT(_.locator("#SpecialsName99_input"))

  val Price = TXT( _.locator("#SpecialsPrice99_input"))
  
  val Description = TXT(_.locator("#SpecialsDialogArea99"))

  val Accessories = TBL( _.locator("#AccessoryTable99"))

  val Specials = TAB(_.locator("#SpecialsTable"))

  // end::fields[]
  given ref: SpecialsDialog_[A] = MYDLG(this,A)

}

object SpecialsDialog_ {
  
}