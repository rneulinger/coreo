package carConf.ui

import coreo.*
import coreo.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class PurchaseOrderDetails_(own: CanOwn) extends Dlg(own) {

  // tag::fields[]
  given ref: OWNER[PurchaseOrderDetails_] = OWNER(this)


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
}

object PurchaseOrderDetails_ {}