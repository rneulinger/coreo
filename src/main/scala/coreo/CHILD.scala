package coreo

import com.microsoft.playwright.options.*

abstract class CHILD
  extends OBJ {

  def own: CanOwn
  final def findFrm(name:String):FRM = own.findFrm(name)
}
