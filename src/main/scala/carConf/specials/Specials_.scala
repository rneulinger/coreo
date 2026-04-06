package carConf.specials

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class Specials_(own: CanOwn) extends DLG(own)
  with coreo.bricks.NewChangDeleteDetailsCancelOK {

  // tag::fields[]
  given ref: OWNER[Specials_] = OWNER(this)

  val Specials = CBX("", (p:Page) => p.
    locator("#SpecialsCombo"))

  val Special = TXT("", (p:Page) => p.
    locator("#SpecialsArea"))

  val BasePrice = TXT("Base Price", (p:Page) => p.
    locator("#BasePrice_input"))

  val SpecialPrice = TXT("Special Price", (p:Page) => p.
    getByText("$4,045.00")) // Wrong

  val AccessoriesPrice = TXT("Accessories Price", (p:Page) => p.
    locator("#AccessoryPrice_input"))

  val Discount = TXT("", (p:Page) => p.
    locator("#DiscountValue_input"))

  val FivePercent = BTN("-5%", (p:Page) => p.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-5%")))

  val FinalPrice = TXT("Final Price", (p:Page) => p.
    getByText("$3,438.25")) // Wrong

  // end::fields[]

}

object Specials_ {

}
