package coreo

import com.microsoft.playwright.*

abstract class WIN(override val own: CanOwn, ui: String = "")
  extends CHILD with CanOwn {
  def app = own.app.asInstanceOf[PwApp]

  def Self = getClass.getName

  final def findWin(name: String): WIN = own.findWin(name)

  val fullType: String = if (ui.isEmpty) myType else ui
  own.adopt(this)

  def path: String

  def pathAbs = path.trim match {
    case "" => ""
    case x if x startsWith ("/") => x
    case x => "/" + x
  }

  override def pg: Page = own.pg

  private var adoptedAtoms = List[Ctrl[?]]()
  lazy val atoms: Map[String, Ctrl[?]] = {
    val tmp = adoptedAtoms.map(a => a.fullName -> a).toMap
    tmp
  }

  override def weight = atoms.map(_._2.weight).sum + 1

  final def adopt(obj: OBJ): Unit =
    obj match {
      case ctrl: Ctrl[_] => adoptedAtoms = adoptedAtoms.appended(ctrl)
      case frm: WIN => own.adopt(obj)
    }

  def onto: WIN = {
    own.onto(this)
    Thread.sleep(200)
    this
  }

  final def openUrl(path: String): Unit = {
    own.openUrl(path)
  }

  def onto(frm: WIN): Unit = own.onto(frm)

  /**
   *
   * @param name
   * @return
   */
  def findAtom(name: String): Option[Ctrl[?]] = {
    if (atoms.keySet.contains(name)) {
      Option(atoms(name)) // exact match
    } else {
      val hits = atoms.values.toList.filter(_.name.contains(name))
      if hits.length == 1 then
        Option(hits.head) // contains match unique
      else
        None
    }
  }

  /**
   * if there is an exact match this one is returned
   * else those which contains name
   *
   * @param name
   * @return
   */
  def findAtoms(name: String): List[Ctrl[?]] = {
    if (atoms.keySet.contains(name)) {
      List(atoms(name)) // exact match
    } else {
      val res = for (n <- atoms.keySet.toList.filter(_.contains(name))) yield {
        atoms(n)
      }
      res
    }
  }

  def dump(string: String): Unit = {
    println(string + myType + "  " + path)

    for (atom <- atoms) {
      val len = atoms.map(_._1.length).max
      val name = atom._1

        println("\t" + name + " " * (len - name.length) + " : " + atom._2.myType + " " + atom._2.name)
    }
  }

  def dump: Unit = dump("")

  def setVar(key: String, value: Any): Unit = own.setVar(key, value)

  def getVar(key: String): String = own.getVar(key)

//  def TAB(func:Page => Locator):TAB[?,?] = ???
def BTN[F <: WIN, T <: WIN](func:Page => Locator=null, idx:Int=0):Btn[F,T] = ???
def TXT(func:Page => Locator=null, idx:Int=0):Txt[?] = ???
def CBX(func:Page => Locator=null, idx:Int=0):Cbx[?] = ???
//  def TBL(func:Page => Locator):TBL[?] = ???
}