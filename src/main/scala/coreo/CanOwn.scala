package coreo

import com.microsoft.playwright.*

/**
 * either Root or WIN
 */
trait CanOwn {
  def app: AnyApp

  /**
   * associated page
   *
   * @return
   */
  def pg: Page

  def companion: Static = {
    val cls = this.getClass // e.g., com.example.X
    //    println(s"search companiaon for: ${cls.getName}")
    val moduleCls = Class.forName(cls.getName + "$") // com.example.X$
    val moduleField = moduleCls.getField("MODULE$")
    moduleField.get(null).asInstanceOf[Static] // the singleton instance (companion object)
  }

  def findWin(name: String): WIN

  final def findWin(comp: Static): WIN = {
    comp match {
      case Unknown_ =>
        println(Unknown_)
      case _ =>
    }
    findWin(comp.name)
  }

  /**
   * convenience method, to use gode generato rsnippet directly
   *
   * @return
   */
  final def page: Page = pg

  def adopt(obj: OBJ): Unit

  def openUrl(path: String): Unit

  def onto(frm: WIN): Unit

  def atoms: Map[String, ATOM[?]]

  final def datas: Map[String, ATOM[?]] = atoms
    .filter(_._2.isInstanceOf[DATA[?]])
    .collect { case d: (String, DATA[?]) => d }

  final def actions: Map[String, ACTION[?, ?]] = atoms
    .filter(_._2.isInstanceOf[ACTION[?, ?]])
    .collect { case a: (String, ACTION[?, ?]) => a }

  def setVar(key: String, value: Any): Unit

  def getVar(key: String): String
}
