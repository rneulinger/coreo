package carConf.vehicles

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*


// tag::fields[]
class Vehicles_(own: AnyApp) extends Dlg(own) {

  val Vehicles = TBL( _.
    locator("#VehicleTablePanel"))

  val BasePrice = TXT(_.
    locator("#BasePrice_input"))

  val SpecialPrice = TXT(_.
    locator("#SpecialPrice_input"))

  val AccessoriesPrice = TXT(_.
    locator("#AccessoryPrice_input"))

  val Discount = TXT(_.
    locator("#DiscountValue_input"))

  @Ui("-5%")
  val FivePercent = BTN(_.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-5%")))

  val FinalPrice = TXT(_.
    locator("#CalculatedPrice_input"))

  // end::fields[]
  given ref: Vehicles_ = this
}
