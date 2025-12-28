package coreo

import com.microsoft.playwright.*

abstract class WIN(override val own: CanOwn, typ: String = "")
  extends CHILD with CanOwn {

  def Self = getClass.getName

  val fullType: String = if (typ.isEmpty) myType else typ
  own.adopt(this)

  def path: String

  override def pg: Page = own.pg

  private var adoptedAtoms = List[ATOM[?]]()
  lazy val atoms: Map[String, ATOM[?]] = {
    val tmp = adoptedAtoms.map(a => a.fullName -> a).toMap
    tmp
  }

  override def weight = atoms.map(_._2.weight).sum + 1

  final def adopt(obj: OBJ): Unit =
    obj match {
      case atom: ATOM[_] => adoptedAtoms = adoptedAtoms.appended(atom)
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

  def findAtom(name: String): Option[ATOM[?]] =
    if (atoms.keySet.contains(name)) {
      Option(atoms(name))
    } else {
      None
    }

  def dump(string: String): Unit = {
    println(string + myType + "  " + path)

    for (atom <- atoms) {
      val len = atoms.map(_._1.length).max
      val name = atom._1
      println("\t" + name + " " * (len - name.length) + " : " + atom._2)
    }
  }

  def dump: Unit = dump("")
}