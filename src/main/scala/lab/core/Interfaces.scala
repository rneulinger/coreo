package lab.core

import coreo.{BackTo, GoTo, NextTo, Return, TBD}

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
     * @return the application to which the object belongs.
     */
    def myApp:App

    /**
     * @return the currently active dialogue.
     */
    def activeDlg:Dlg

    /**
     * performs validation on the current object.
     * there will be validations in the future
     */
    def validate():Unit

    /**
     * get all annotations defined on class level for this object
     * @return
     */
    final def classAnnotations:List[Annotation] = {
      val anis  = for ( itf <- getClass.getAnnotatedInterfaces ) yield {
        itf.getAnnotations.toList
      }
      //println( "Sup:" + getClass.getAnnotatedSuperclass.toList )
      getClass.getAnnotations.toList ++ anis.flatten
    }

    /**
     * returns all fields derived from Obj for this instance
     *
     * @return
     */
    def myObjs: List[(String, Obj)] = collectMembersOfType(this, classOf[Obj])

    /**
     * return the name of the given object if it's a field.
     * @param obj to search for
     * @return unique name or None if obj is not a field
     */
    def nameOfObj(obj: _Obj): Option[String] = {
      myObjs.filter(_._2 == obj) match {
        case Nil => None
        case head :: tail => Some(head._1)
      }
    }

    /**
     *
     * @param obj
     * @return
     */
    def annotationsForObj(obj: _Obj): List[Annotation] = {
      nameOfObj(obj) match{
        case None => Nil
        case Some(name) =>
          val field = this.getClass.getDeclaredField(name)
          field.setAccessible(true)
          field.getAnnotations.toList
      }
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
    final def goAnnotations:List[GoTo] = myAnnotations.collect{ case a:GoTo => a }
    final def toAnnotations:List[NextTo] = myAnnotations.collect{ case a:NextTo => a }
    final def uiAnnotations:List[coreo.Ui] = myAnnotations.collect{ case a:coreo.Ui => a }
    final def tbdAnnotations: List[TBD] = myAnnotations.collect { case a: TBD => a }
    final def retAnnotations: List[Return] = myAnnotations.collect { case a: Return => a }

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
    def TXT(id:String=""):Txt
    def BTN(id:String=""):Btn
    def SUB(id:String=""):SubBtn
    def RET(id:String=""):RetBtn
    def NXT(id:String=""):NextBtn
    def BAK(id:String=""):BackBtn

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

  /**
   * a normal button, that fires some internal aktion, but not leeaving the page
   */
  trait Btn extends Action

  trait Marker
  /**
   * navigate to the next dialog
   */
  @TBD
  trait IsNext extends Marker

  /**
   * navigat eto the previous dialog
   */
  @BackTo
  trait IsBack extends Marker

  /**
   * invoke a sub-dialog.
   * Cancel, Ok, Finish usually return to the current dialog
   */
  @TBD
  trait IsSub extends Marker

  /**
   * return to the dialog that has called this dialog with a Sub-Acction
   */

  @Return
  trait IsRet extends Marker

  /**
   *
   */

  trait NextBtn extends Btn with IsNext

  /**
   * a button that navigates to the previous dialog, annotated with Back
   */
  trait BackBtn extends Btn with IsBack

  /**
   * a button that navigates to the previous dialog, annotated with Back
   */
  trait RetBtn extends Btn with IsRet

  /**
   *
   */
  trait SubBtn extends Btn with IsSub

}
