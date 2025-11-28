package coreo

import com.microsoft.playwright.{Locator, Page}

/**
 * spezielle Kokatoren, die bei Ids zum einsatz kommen.
 */
abstract class IdLoc extends Function1[Page, Locator] {
  def id : String
}

case class IdBTN( id:String) extends IdLoc{
  //override def apply(page: Page): Locator = page.locator(s"[id=\"$id\"] > #button")
  override def apply(page: Page): Locator = page.locator(s"button#$id")
}

case class IdMIT( id:String) extends IdLoc{
  override def apply(page: Page): Locator = page.locator(s"menu-bar-button#$id")
}

case class IdTXT( id:String) extends IdLoc{
  override def apply(page: Page): Locator = page.locator(s"input#$id")
}

case class IdCBX( id:String) extends IdLoc{
  override def apply(page: Page): Locator = page.locator(s"combobox#$id")
}

case class IdCHK( id:String) extends IdLoc{
  override def apply(page: Page): Locator = page.locator(s"checkbox#$id")
}

case class IdRBT( id:String) extends IdLoc{
  override def apply(page: Page): Locator = page.locator(s"radio#$id")
}