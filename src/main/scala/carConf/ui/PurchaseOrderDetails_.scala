package carConf.ui

import coreo.*
import coreo.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class PurchaseOrderDetails_(own: CanOwn) extends FRM(own) {

  // tag::fields[]
  given ref: OWNER[PurchaseOrderDetails_] = OWNER(this)


  val SelectedVehicle = TXT("Selected vehicle")

  val Price = TXT("")

  val Discount = TXT("")

  val Total = TXT("")

  val SelectedSpecial = TXT("Selected special")

  val PriceSpecialDetails = TXT("Price special details")

  val SelectedAccessory = TXT("Selected accessory")

  val Result = TXT("")

  val FinalPrice = TXT("Final price")

  val CollectionExWorks = TXT("Collection ex works")

  val CollectionExVendor = TXT("Collection ex vendor")

  val Deliver = TXT("")

  val SendPurchasEorder = TXT("Send purchas eorder")

  val Cancel = BTN("")


  // end::fields[]
}

object PurchaseOrderDetails_ {}