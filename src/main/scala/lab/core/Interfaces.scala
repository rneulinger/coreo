package lab.core

import scala.annotation.StaticAnnotation

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

    def GoAnnotations():Array[String]
    def ToAnnotations(): Array[Class[? <: Interfaces.Dlg]]
    def UiAnnotations(): Array[String]
    def TbdAnnotations(): Array[Tbd]
    def ReturnAnnotations(): Array[Ret]

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


import java.lang.annotation.{Retention, RetentionPolicy, Target, ElementType}

/**
 * defines a name for a ui component
 * in complicated cases it is required to use a special name eg "%"
 */
@Retention(RetentionPolicy.RUNTIME)
class Ui(val value: String) extends StaticAnnotation

/**
 * path for direct invocations
 * @param value
 */
@Retention(RetentionPolicy.RUNTIME)
class Go(val value: String) extends StaticAnnotation

/**
 * apply this to an action-ctrl if it returns to the previous dialog
 */
@Retention(RetentionPolicy.RUNTIME)
class Ret() extends StaticAnnotation

/**
 * To Be Defined, apply this to an action-ctrl if it requires to define the target in derived dialogs
 */
@Retention(RetentionPolicy.RUNTIME)
class Tbd() extends StaticAnnotation

@Retention(RetentionPolicy.RUNTIME)
@Target(Array(ElementType.TYPE))
class To(val dest: Class[? <: Interfaces.Dlg]) extends StaticAnnotation
