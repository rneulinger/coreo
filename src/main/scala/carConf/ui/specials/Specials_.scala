package carConf.ui.specials

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

// tag::fields[]
class Specials_(own: AnyApp) extends Dlg(own)
  with coreo.bricks.NewChangDeleteDetailsCancelOK {

  val Specials = CBX( _.
    locator("#SpecialsCombo"))

  val Special = TXT(_.
    locator("#SpecialsArea"))

  val BasePrice = TXT(_.
    locator("#BasePrice_input"))

  val SpecialPrice = TXT(_.
    getByText("$4,045.00")) // Wrong

  val AccessoriesPrice = TXT(_.
    locator("#AccessoryPrice_input"))

  val Discount = TXT(_.
    locator("#DiscountValue_input"))

  @Ui("-5%")
  val FivePercent = BTN(_.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-5%")))

  val FinalPrice = TXT( _.
    getByText("$3,438.25")) // Wrong

  // end::fields[]
  given ref: Specials_ = this
}

object Specials_ {}