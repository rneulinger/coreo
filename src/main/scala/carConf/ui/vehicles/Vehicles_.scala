package carConf.ui.vehicles

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*


class Vehicles_(own: CanOwn) extends DLG(own) {

  // tag::fields[]
  given ref: OWNER[Vehicles_] = OWNER(this)

  val Vehicles = TBL("", (p:Page) => p.
    locator("#VehicleTablePanel"))

  val BasePrice = TXT("Base Price", (p:Page) => p.
    locator("#BasePrice_input"))

  val SpecialPrice = TXT("Special Price`", (p:Page) => p.
    locator("#SpecialPrice_input"))

  val AccessoriesPrice = TXT("Accessories Price", (p:Page) => p.
    locator("#AccessoryPrice_input"))

  val Discount = TXT("", (p:Page) => p.
    locator("#DiscountValue_input"))

  val FivePercent = BTN("-5%", (p:Page) => p.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-5%")))

  val FinalPrice = TXT("Final Price", (p:Page) => p.
    locator("#CalculatedPrice_input"))

  // end::fields[]
}

object Vehicles_ {}