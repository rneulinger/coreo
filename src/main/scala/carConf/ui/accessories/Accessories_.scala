package carConf.ui.accessories

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class Accessories_(own: CanOwn) extends DLG(own) {

  // tag::fields[]

  given ref: OWNER[Accessories_] = OWNER(this)

  val Accessories = TBL("", (p:Page) => p.
    locator("#AccessoryTable"))

  val AddAccessoriesPriceToFinalPrice = CBX("Add accessories price to final price", (p:Page) => p.
    getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Add accessories price to")))

  val BasePrice = TXT("Base price", (p:Page) => p.
    locator("#BasePrice_input"))

  val SpecialPrice = TXT("Special price", (p:Page) => p.
    getByText("$4,045.00")) // wrong

  val AccessoriesPrice = TXT("Accessories price", (p:Page) => p.
    locator("#AccessoryPrice_input"))

  val Discount = TXT("", (p:Page) => p.
    locator("#DiscountValue_input"))

  val FivePercent = BTN("-5%", (p:Page) => p.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-5%")))

  val FinalPrice = TXT("Final price", (p:Page) => p.
    getByText("$3,236.00")) // Wrong

  // end::fields[]
}

object Accessories_ {}