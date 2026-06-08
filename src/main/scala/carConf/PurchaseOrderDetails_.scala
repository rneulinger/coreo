package carConf

import coreo.*
import coreo.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class PurchaseOrderDetails_[+A <: App](using myApp: App) extends Dlg {

  // tag::fields[]

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

  val Deliver = TXT()

  val SendPurchaseOrder = TXT()

  val Cancel = BTN()


  // end::fields[]
  given myDlg: PurchaseOrderDetails_[A] = this
}