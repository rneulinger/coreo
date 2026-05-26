package coreo

import com.microsoft.playwright.*

/**
 * either Root or WIN
 */
trait CanOwnXXX {
  def app: App

  /**
   * associated page
   *
   * @return
   */
  def pg: Page
  final def page: Page = pg

  def companion: Static = {
    val cls = this.getClass // e.g., com.example.X
    //    println(s"search companiaon for: ${cls.getName}")
    val moduleCls = Class.forName(cls.getName + "$") // com.example.X$
    val moduleField = moduleCls.getField("MODULE$")
    moduleField.get(null).asInstanceOf[Static] // the singleton instance (companion object)
  }

  def findWin(name: String): Dlg

  final def findWin(comp: Static): Dlg = {
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

//  def adopt(obj: OBJ): Unit

  def openUrl(path: String): Unit

  def onto(frm: Dlg): Unit

  def atoms: Map[String, ACtrl]

  final def datas: Map[String, ACtrl] = atoms
    .filter(_._2.isInstanceOf[Data[?]])
    .collect { case d: (String, Data[?]) => d }

  final def actions: Map[String, Action[?]] = atoms
    .filter(_._2.isInstanceOf[Action[?]])
    .collect { case a: (String, Action[?]) => a }

  def setVar(key: String, value: Any): Unit

  def getVar(key: String): String
}
