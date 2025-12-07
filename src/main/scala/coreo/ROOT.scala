package coreo

abstract class ROOT extends OBJ with CanOwn {
  private var adoptedAtoms = List[ATOM[?]]()
  private var adoptedFrms = List[FRM]()

  lazy val atoms: Map[String, ATOM[?]] = adoptedAtoms.map(a => a.fullName -> a).toMap

  lazy val (short, full, frms)  = {
    val short = adoptedFrms.map(a => a.myType -> a).toMap
    val full = adoptedFrms.map(a => a.fullType -> a).toMap
    (short, full, short ++ full)
  }

  /**
   * variables for test execution
   */
  private var VARS = Map[String, Any]()

  def setVar(key: String, value: Any): Unit = {
    val res = VARS + (key -> value)
    VARS = res
  }

  def getVar(key: String): Any = {
    VARS.get(key)
  }

  def getVarOrElse(key: String, default: Any): Any = {
    VARS.getOrElse(key, default)
  }

  final def adopt(obj: OBJ): Unit =
    obj match {
      case atom: ATOM[_] =>
        adoptedAtoms = adoptedAtoms.appended(atom)

      case frm: FRM =>
        adoptedFrms = adoptedFrms.appended(frm)
    }

  var currentFrm:FRM  = new FRM(this){}
  val defaultFrm = currentFrm
  /**
   * visit: push current view on stack, arg becomes current,
   * return: push curren tin history, pop and set current
   */
  val viewStack = scala.collection.mutable.Stack[FRM]()
  val viewHistory = scala.collection.mutable.Stack[FRM]()

  def onto( frm:FRM):Unit = {
    viewStack.push(currentFrm)
    currentFrm = frm
    println( "changed to frm:"+ frm.myType)
    println( frm.dump )
  }

  def goto(name: String): Unit = {
    val dest = Defs.mkCamelCase(name)
    if (frms.keySet.contains(dest)) {
      frms(dest).goto
    } else{
      println( "goto: cannot find frame:" + dest )
    }
  }

  def onto( name:String): Unit = {
    val dest = Defs.mkCamelCase(name)
    if ( frms.keySet.contains(dest) ){
      onto( frms(dest))
    } else{
      println( "onto: cannot find frame:" + dest )
    }
  }

  def back:Unit = {
    if (viewStack.nonEmpty) {
      currentFrm = viewStack.pop()
    } else{
      currentFrm = defaultFrm
    }
  }

  final def dump(s: String = ""): Unit = {
    println(atoms)
    val hits = frms.filter(_._1.contains(s))
    for (frm <- hits) {
      println()
      frm._2.dump
    }
  }

}

