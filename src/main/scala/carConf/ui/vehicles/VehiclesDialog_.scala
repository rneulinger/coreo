package carConf.ui.vehicles

import coreo.*
import coreo.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class VehiclesDialog_(own: AnyApp) extends Dlg(own) {

  // tag::fields[]
  given ref: OWNER[VehiclesDialog_] = OWNER(this)

  val Vehicles = TBL( _.
    locator("#VehiclesTable"))

  val VehicleName = TXT(_.
    locator("#VehicleName_input"))

  val ID = TXT( _.
    locator("#VehicleId_input"))

  val Price = TXT( _.
    locator("#VehiclePrice_input"))

  val New = BTN(_.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New")))

  val Change = BTN(_.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Change")))

  val Delete = BTN(_.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")))

  val OK = BTN(_.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK")))

  val Cancel = BTN(_.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel")))

  // end::fields[]
}

object VehiclesDialog_ {}