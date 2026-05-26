package carConf.specials

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class Specials_[+A <: App](using app: App) extends Dlg
  with coreo.bricks.NewChangDeleteDetailsCancelOK {

  // tag::fields[]

  val Specials = CBX( _.locator("#SpecialsCombo"))

  val Special = TXT(_.locator("#SpecialsArea"))

  val BasePrice = TXT(_.locator("#BasePrice_input"))

  val SpecialPrice = TXT(_.getByText("$4,045.00")) // Wrong

  val AccessoriesPrice = TXT( _.locator("#AccessoryPrice_input"))

  val Discount = TXT(_.locator("#DiscountValue_input"))

  @Ui("-5%")
  val FivePercent = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-5%")))

  val FinalPrice = TXT(_.getByText("$3,438.25")) // Wrong

  // end::fields[]
  override given myDlg: Specials_[A] = this

}

object Specials_ {

}
