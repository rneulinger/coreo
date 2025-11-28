package coreo

abstract class ROOT extends OBJ with CanOwn {
  /**
   * variables for test execution
   */
  private var VARS = Map[String, Any]()
  def setVar( key:String, value:Any): Unit = {
    val res = VARS + (key -> value)
    VARS = res
  }
  def getVar( key:String): Any = {VARS.get(key)}
  def getVarOrElse( key:String, default:Any): Any = {VARS.getOrElse(key, default)}
  def vars = VARS
}

