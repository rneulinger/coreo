package carConf.specials

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class Specials_(own: CanOwn) extends FRM(own)
  with coreo.bricks.NewChangDeleteDetailsCancelOK {

  // tag::fields[]
  given ref: OWNER[Specials_] = OWNER(this)

  val Specials = CBX("",
    _.locator("#SpecialsCombo"))

  val Special = TXT("",
    _.locator("#SpecialsArea"))

  val BasePrice = TXT("Base Price",
    _.locator("#BasePrice_input"))

  val SpecialPrice = TXT("Special Price",
    _.getByText("$4,045.00")) // Wrong

  val AccessoriesPrice = TXT("Accessories Price",
    _.locator("#AccessoryPrice_input"))

  val Discount = TXT("",
    _.locator("#DiscountValue_input"))

  val FivePercent = BTN("-5%",
    _.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-5%")))

  val FinalPrice = TXT("Final Price",
    _.getByText("$3,438.25")) // Wrong

  // end::fields[]

}

object Specials_ {

}
