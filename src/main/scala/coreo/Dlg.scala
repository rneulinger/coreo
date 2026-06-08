package coreo

import com.microsoft.playwright.*

abstract class Dlg(using val myApp: App)
  extends Obj with Destination: //with CanOwn {
  def app = myApp.app

  def Self = getClass.getName

  final def findWin(name: String): Dlg = app.findWin(name)

  val fullType: String = ??? //if (ui.isEmpty) myType else ui
  app.adopt(this)

  def path: String = ""

  def goto(): Unit = ???

  def pathAbs = path.trim match {
    case "" => ""
    case x if x.startsWith("/") => x
    case x => "/" + x
  }

  override def pg: Page = app.pg

  private var adoptedAtoms = List[Ctrl[?]]()

  final def datas: Map[String, ACtrl] = atoms
    .filter(_._2.isInstanceOf[Data[?]])
    .collect { case d: (String, Data[?]) => d }

  final def actions: Map[String, Action[?]] = atoms
    .filter(_._2.isInstanceOf[Action[?]])
    .collect { case a: (String, Action[?]) => a }

  lazy val atoms: Map[String, ACtrl] = {
    val tmp = adoptedAtoms.map(a => a.fullName -> a).toMap
    tmp
  }

  def weight = atoms.map(_._2.weight).sum + 1

  final def adopt(ctrl: Ctrl[?]): Unit = {
    adoptedAtoms = adoptedAtoms.appended(ctrl)
  }


  def onto: Dlg = {
    app.onto(this)
    Thread.sleep(200)
    this
  }

  final def openUrl(path: String): Unit = {
    app.asInstanceOf[PwApp].openUrl(path)
  }

  def onto(frm: Dlg): Unit = app.onto(frm)

  /**
   * Attempts to locate an `ACtrl` instance by name using two matching strategies:
   *
   *  1. **Exact match**
   *     If the provided `name` exactly matches a key in the `atoms` map,
   *     the corresponding `ACtrl` is returned.
   *
   *  2. **Unique substring match**
   *     If no exact match exists, the method searches all `ACtrl` instances
   *     whose `name` field contains the given `name` as a substring.
   *     If exactly one such hit exists, that instance is returned.
   *
   * If neither an exact match nor a unique substring match is found,
   * the method returns `None`.
   *
   * @param name
   * The lookup string used to identify an `ACtrl` instance.
   * @return
   * `Some(ACtrl)` if an exact match exists, or if exactly one substring match
   * is found; otherwise `None`.
   */
  def findAtom(name: String): Option[ACtrl] = {
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
  def findAtoms(name: String): List[ACtrl] = {
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

  def setVar(key: String, value: Any): Unit = app.setVar(key, value)

  def getVar(key: String): String = app.getVar(key)

  //  def TAB(func:Page => Locator):TAB[?,?] = ???
  def BTN[F <: Dlg](func: Page => Locator = null, idx: Int = 0): Btn[?] = ???

  def TXT(func: Page => Locator = null, idx: Int = 0): Txt[?] = ???

  def CBX(func: Page => Locator = null, idx: Int = 0): Cbx[?] = ???

  def TAB(func: Page => Locator = null, idx: Int = 0): Tab[?] = ???

  def TBL(func: Page => Locator = null, idx: Int = 0): Tbl[?] = ???
//  def TBL(func:Page => Locator):TBL[?] = ???
