package carConf.accessories

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

// tag::fields[]
class Accessories_[+A <: App](using app: App) extends Dlg {

  val BasePrice = TXT(_.locator("#BasePrice_input"))

  val SpecialPrice = TXT(_.getByText("$4,045.00")) // wrong

  val AccessoriesPrice = TXT( _.locator("#AccessoryPrice_input"))

  val Discount = TXT(_.locator("#DiscountValue_input"))

  @Ui("-5%")
  val FivePercent = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-5%")))

  val FinalPrice = TXT(_.getByText("$3,236.00")) // Wrong

  val AddAccessoriesPriceToFinalPrice = CBX(_.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Add accessories price to")))

  val Accessories = TBL( _.locator("#AccessoryTable"))
  
  def fump(): Unit = {
    val own=BasePrice.dlg
    
  }
  // end::fields[]
  given ref: Accessories_[A] = this
}