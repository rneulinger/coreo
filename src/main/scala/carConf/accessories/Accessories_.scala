package carConf.accessories

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class Accessories_(own: CanOwn) extends FRM(own) {

  // tag::fields[]
  given ref: OWNER[Accessories_] = OWNER(this)

  val Accessories = TBL("",
    _.locator("#AccessoryTable"))

  val AddAccessoriesPriceToFinalPrice = CBX("Add accessories price to final price",
    _.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Add accessories price to")))

  val BasePrice = TXT("Base price",
    _.locator("#BasePrice_input"))

  val SpecialPrice = TXT("Special price",
    _.getByText("$4,045.00")) // wrong

  val AccessoriesPrice = TXT("Accessories price",
    _.locator("#AccessoryPrice_input"))

  val Discount = TXT("",
    _.locator("#DiscountValue_input"))

  val FivePercent = BTN("-5%",
    _.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-5%")))

  val FinalPrice = TXT("Final price",
    _.getByText("$3,236.00")) // Wrong


  // end::fields[]
}