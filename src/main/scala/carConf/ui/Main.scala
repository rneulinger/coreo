package carConf.ui

import coreo.*
import carConf.ui.specials.*
import carConf.ui.accessories.{AccessoriesDialog, Accessories}
import carConf.ui.specials.{SpecialsDialog, Specials}
import carConf.ui.vehicles.{VehiclesDialog, Vehicles}
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

/*
{
  import $ivy.`com.microsoft.playwright:playwright:1.53.0`
  import $cp.`./build/libs/burli.jar`
  import com.microsoft.playwright.*
  import com.microsoft.playwright.options.*
  import burli.*
  import carConf.*
}
*/
val s = "file:///C:/Projects/burli/carconfigWeb/html/CarConfig.htm?lang=en#"
class Main() extends PwRoot(s) {

  // TODO Mene
  val _CarConfig = CarConfig(this)
  val _SpecialsTab = _CarConfig.SpecialsTab
  val _VehiclesTab = _CarConfig.VehiclesTab
  val _AccessoriesTab = _CarConfig.AccessoriesTab

  val _Specials = Specials(this)
  val _SpecialsDialog = SpecialsDialog(this)

  val _Vehicles = Vehicles(this)
  val _VehiclesDialog = VehiclesDialog(this)

  val _Accessories = Accessories(this)
  val _AccessoriesDialog = AccessoriesDialog(this)

  object specials {
  }

  enum Menu {
    case File
    case File_Reset
    case Options
    case Options_Vehicles
    case Options_Specials
    case Options_Accessories
    case PurchaseOrder
    case PurchaseOrder_ViewSelectedDetails
    case PurchaseOrder_SendOrder
    case Help
    case Help_Info
    case Help_Buggy
    case Help_LoadTestingModule
  }

  def goto(mnu:Menu) = {
    def click(s:String): Unit = {
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(s)).click();
    }
    def click2(s:String, s2:String): Unit = {
      click(s)
      click(s2)
    }
    mnu match {
      case Menu.File => click("File")
      case Menu.File_Reset => click2("File", "Reset")
      case Menu.Options => click("Options")
      case Menu.Options_Vehicles => click2("Options", "Vehicles...")
      case Menu.Options_Specials => click2("Options", "Specials...")
      case Menu.Options_Accessories=> click2("Options", "Accessories...")
      case Menu.PurchaseOrder => click("Purchase order" )
      case Menu.PurchaseOrder_ViewSelectedDetails  => click2("Purchase order", "View selected details")
      case Menu.PurchaseOrder_SendOrder => click2("Purchase order","Send order")
      case Menu.Help => click("Help")
      case Menu.Help_Info => click2("Help","Info")
      case Menu.Help_Buggy => click2("Help","Buggy")
      case Menu.Help_LoadTestingModule => click2("Help","Load testing mode")
    }
  }
}


