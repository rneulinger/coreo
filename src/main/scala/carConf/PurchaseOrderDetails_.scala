package carConf

import coreo.*
import coreo.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class PurchaseOrderDetails_(own: CanOwn) extends DLG(own) {

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

  val Deliver = TXT("")

  val SendPurchaseOrder = TXT("Send purchase order")

  val Cancel = BTN("")


  // end::fields[]
}