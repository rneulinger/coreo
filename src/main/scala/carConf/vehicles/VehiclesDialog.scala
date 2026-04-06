package carConf.vehicles

import coreo.*
import coreo.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class VehiclesDialog(own: CanOwn) extends DLG(own) {

  // tag::fields[]
  given ref: OWNER[VehiclesDialog] = OWNER(this)

  val Vehicles = TBL("", (p:Page) => p.
    locator("#VehiclesTable"))

  val VehicleName = TXT("Vehicle name", (p:Page) => p.
    locator("#VehicleName_input"))

  val ID = TXT("", (p:Page) => p.
    locator("#VehicleId_input"))

  val Price = TXT("", (p:Page) => p.
    locator("#VehiclePrice_input"))

  val New = BTN("", (p:Page) => p.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New")))

  val Change = BTN("", (p:Page) => p.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Change")))

  val Delete = BTN("", (p:Page) => p.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")))

  val OK = BTN("", (p:Page) => p.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK")))

  val Cancel = BTN("", (p:Page) => p.
    getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel")))

  // end::fields[]
}