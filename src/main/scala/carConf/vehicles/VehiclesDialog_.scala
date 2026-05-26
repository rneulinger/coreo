package carConf.vehicles

import coreo.*
import coreo.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

// tag::fields[]
class VehiclesDialog_[+A <: App](using own: PwApp) extends Dlg {

  val Vehicles = TBL( _.locator("#VehiclesTable"))

  val VehicleName = TXT(_.locator("#VehicleName_input"))

  val ID = TXT( _.locator("#VehicleId_input"))

  val Price = TXT( _.locator("#VehiclePrice_input"))

  val New = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New")))

  val Change = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Change")))

  val Delete = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")))

  val OK = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK")))

  val Cancel = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel")))

  // end::fields[]
  given ref: VehiclesDialog_[A] = this
}