package carConf.ui

import coreo.*
import coreo.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

// tag::fields[]
class PurchaseOrderDetails_(own: AnyApp) extends Dlg(own) {



  val SelectedVehicle = TXT()

  val Price = TXT()

  val Discount = TXT()

  val Total = TXT()

  val SelectedSpecial = TXT()

  val PriceSpecialDetails = TXT()

  val SelectedAccessory = TXT()

  val Result = TXT()

  val FinalPrice = TXT()

  val CollectionExWorks = TXT()

  val CollectionExVendor = TXT()

  val Deliver = TXT()

  val SendPurchasEorder = TXT()

  val Cancel = BTN()


  // end::fields[]
  given ref: PurchaseOrderDetails_ = this
}

object PurchaseOrderDetails_ {}