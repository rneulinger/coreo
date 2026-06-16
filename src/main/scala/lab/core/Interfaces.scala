package lab.core

import scala.annotation.StaticAnnotation
import java.lang.annotation.Annotation
import lab.utils.collectMembersOfType

/**
 * public interface for important objects within the problem domain
 */
object Interfaces {

  /**
   * common base for all elements of an application.
   * each element know the application and the current dialog,
   * furthermore a validate method is defined to detect potential problems
   */
  trait Obj:
    /**
     *
     * @return the application to which the object belongs
     */
    def myApp:App

    /**
     * @return the currently active dialogue
     */
    def actDialog:Dlg

    /**
     * performs validation on the current object
     */
    def validate():Unit

    /**
     * get all annotations defined on class level for this object
     * @return
     */
    final def classAnnotations = {
      getClass.getAnnotations.toList
    }

    /**
     * returns all fields derived from Obj for this instance
     * @return
     */
    def myObjs = collectMembersOfType(this, classOf[_Obj])

    /**
     * return the name of the given object if it's a field.
     * @param obj to search for
     * @return unique name or "" if obj is not a field
     */
    def nameForObj(obj: _Obj): String = {
      myObjs.filter(_._2 == obj) match {
        case Nil => ""
        case head :: tail => head._1
      }
    }

    /**
     *
     * @param obj
     * @return
     */
    def annotationsForObj(obj: _Obj) = {
      val name = nameForObj(obj)
      val field = this.getClass.getDeclaredField(name)
      field.setAccessible(true)
      field.getAnnotations.toList
    }

    /**
     * return all annotations for this object defined on parent level
     * must be implemented in derived classes if appropriate
     * @return
     */
    def parentAnnotations = List[Annotation]()
    /**
     * get all defined annotations either by class or field
     * @return
     */
    final def myAnnotations:List[Annotation] = classAnnotations ++ parentAnnotations

    /**
     * return all Go-annotations
     * @return
     */
    final def goAnnotations:List[coreo.Go] = myAnnotations.collect{ case a:coreo.Go => a }
    final def toAnnotations:List[coreo.To] = myAnnotations.collect{ case a:coreo.To => a }
    final def uiAnnotations:List[coreo.Ui] = myAnnotations.collect{ case a:coreo.Ui => a }
    final def tbdAnnotations: List[coreo.Tbd] = myAnnotations.collect { case a: coreo.Tbd => a }
    final def retAnnotations: List[coreo.Ret] = myAnnotations.collect { case a: coreo.Ret => a }

  /**
   * collection of dialogs.
   */
  trait App extends Obj:
    /**
     * @return all dialogs of this application
     */

    def dlgMembers:List[(String, Dlg)]

    /**
     *
     * @return all dialogs having a Go-annotation
     */
    def gotoMembers:List[(String, Dlg)]

    /**
     * navigates directly to a dialog.
     * The specified path is separated by / and must be interpreted by the application.
     * If it does not contain a / it is treated as name of the dialog
     *
     * @param path
     */
    def goTo(path: String = "/"):Unit

    /**
     * navigates directly to a dialog.
     * @param dlg the specified dialog must have an Go annotation
     */
    def goTo[T <: _Dlg](dlg: Class[T]):Unit

    /**
     * the given dialog becomes the active one, without navigation
     * @param dlgName either a valid path (as specified in Go-annotations), or a name of a dialog
     */
    def onTo(dlgName: String):Unit

    /**
     * the given dialog becomes the active one, without navigation
     *
     * @param dlg
     * @tparam T
     */
    def onTo[T <: _Dlg](dlg: Class[T]):Unit

    /**
     * the dialog with no elements.
     * @return
     */
    def Unknown: _Dlg


  /**
   * collection of controls
   */
  trait Dlg extends Obj:

    /**
     * all controls of this dialog
     * @return
     */
    def ctrlMembers:List[(String, Ctrl)]

    /**
     * all data controls
     * @return
     */
    def dataMembers: List[(String, Data)]

    /**
     * all action controls
     * @return
     */
    def actionMembers: List[(String, Action)]

  /**
   * base for all controls.
   */
  trait Ctrl extends Obj:
    /**
     * every control belong to exactly one dialog
     * @return
     */
    def myDlg:Dlg

  /**
   * base for all data related controls. Text, Listbox, Combobox ....
   */
  trait Data extends Ctrl

  trait Txt extends Data

  /**
   * base for all action related controls
   */
  trait Action extends Ctrl
  trait Btn extends Data
}
