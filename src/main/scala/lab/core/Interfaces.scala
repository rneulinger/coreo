package lab.core

import coreo.{BackTo, GoTo, NextTo, Return, TBD}

import scala.annotation.StaticAnnotation
import java.lang.annotation.Annotation
import lab.utils.collectMembersOfType

/**
 * public interface for important objects within the problem domain
 */
object Interfaces:

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
    def instanceName:String

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
    def nameOfObj(obj: Obj): Option[String] = {
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
    def annotationsForObj(obj: Obj): List[Annotation] = {
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

  trait Can:
    def click( ctrl:String):Unit

    def set( ctrl: String, value:Any): Unit
    def expect( ctrl: String, valueOf: Any ) :Unit

  /**
   * collection of dialogs.
   * goTo <=> goBack
   *
   */
  trait App extends Obj with Can:
    /**
     * @return all dialogs of this application
     */

    def allDlgs:List[(String, Dlg)]

    /**
     *
     * @return all dialogs having a Go-annotation
     */
    def gotoDlgs:List[(String, Dlg)]

    /**
     * find all dialogs matching the given parameter
     * @param dlg
     *   case T <: Dlg type of dialog recommended
     *   case String
     *     if all lowercase = treat as path separated by /
     *     if contains uppercase && "." treat a type
     * @tparam T type of dialog
     * @return
     */

    def findDlgs[T <: Dlg](dlg: Class[T] | String | Dlg):List[(String,Dlg)]

    /**
     * find the unique dialog that matches the given parameter
     * @param dlg to find
     * @tparam T the type of dialog to find
     * @return the dialog
     * @throws exeption if NOT unique OR NOT found
     * @see findDialogs
     */
    def findDlg[T <: Dlg](dlg: Class[T] | String | Dlg):(String,Dlg)
    /**
     * navigates directly to a dialog.
     * the dialog must have a goto-annotation
     * The specified path is separated by / and must be interpreted by the application.
     * If it does not contain a / it is treated as name of the dialog
     * @param dlg the specified dialog must have an Go annotation
     *
     */
    def goTo[T <: Dlg](dlg: Class[T] | Dlg | String = "/"):Unit
    def goBack():Unit

    /**
     * the given dialog becomes the active one, without navigation
     * the active dialog is stored of the stack
     * @param dlg either a valid path (as specified in Go-annotations), or a name of a dialog
     *
     * @tparam T
     */
    def nextTo[T <: Dlg](dlg: Class[T]|String):Unit

    /**
     * the dialog from the stack becomes the active window
     * if the stack is empty "Unknow" becomes the active dialog and a warning is logged
     */
    def backTo() :Unit

    /**
     * the given dialog becomes the active one, without navigation.
     * the active dialog can be restored with leave.
     * the current next-stack is saved and a fresh one is created
     * if the new dialog does not have a control of type RET a warning is logged
     * @param dlg
     * @tparam T
     */
    def inTo[T <: Dlg](dlg: Class[T]|String):Unit

    /**
     * returns to the most recent dialog of the sub-stack
     * if the stack is empty "Unknow" becomes the active dialog and a warning is logged
     * the current next-stack is dropped and the previously save is restored (like return)
     */
    def leave():Unit

    /**
     * the dialog with no elements.
     * @return
     */
    def Unknown: Dlg


  /**
   * collection of controls
   */
  trait Dlg extends Obj with Can:
    def click( name:String):Unit
    /**
     * Helper to create default Txt
     * @param id unique id of this control if defined, default is "" which means no id
     * @return
     */
    def TXT(id:String=""):Txt
    def BTN(id:String=""):Btn
    def SUB(id:String=""):InToBtn
    def RET(id:String=""):RetBtn
    def NXT(id:String=""):NextBtn
    def BAK(id:String=""):BackBtn

    /**
     * all controls of this dialog
     * @return
     */
    def allCtrls:List[(String, Ctrl)]

    /**
     * all data controls
     * @return
     */
    def allDatas: List[(String, Data)]

    /**
     * all action controls
     * @return
     */
    def allActions: List[(String, Action)]

    def findCtrls[T <: Ctrl](ctrl: Class[T] | Ctrl |String): List[(String, Ctrl)]

    def findCtrl[T <: Ctrl](ctrl: Class[T] | Ctrl| String): (String, Ctrl)
  /**
   * base for all controls.
   */
  trait Ctrl extends Obj:
    /**
     * every control belong to exactly one dialog
     * @return
     */
    def myDlg:Dlg
    def click():Unit
    def set( value:Any):Unit
    def expect(value:Any):Unit

  /**
   * base for all data related controls. Text, Listbox, Combobox ....
   */
  trait Data extends Ctrl

  trait Txt extends Data

  /**
   * base for all action related controls
   */
  trait Action extends Ctrl:
    /**
     * force to define the destination of an action.
     * if set it overrides annotations. often used in conjunction with TBD annotation
     */
    var dest:Option[Dlg] = None

  /**
   * a normal button, that fires some internal aktion, but not leeaving the page
   */
  trait Btn extends Action

  /**
   * marker interface to define the behavior of actions   
   */
  trait Marker
  /**
   * this control navigates to the next dialog
   */
  @TBD
  trait IsNext extends Marker

  /**
   * this control navigates to the previous dialog
   */
  @BackTo
  trait IsBack extends Marker

  /**
   * this control invokes a sub-dialog (gosub).
   * Cancel, Ok, Finish usually return to the current dialog
   */
  @TBD
  trait IsInTo extends Marker

  /**
   * this control leaves the sub dialog that has called this dialog by a sub-action (return).
   */

  @Return
  trait IsRet extends Marker

  /**
   * a button that navigates to the next dialog, annotated with IsNext 
   */

  trait NextBtn extends Btn with IsNext

  /**
   * a button that navigates to the previous dialog, annotated with IsBack
   */
  trait BackBtn extends Btn with IsBack

  /**
   * a button that invoke a child dialog, annotated with IsSub
   */
  trait InToBtn extends Btn with IsInTo

  /**
   * a button that return to the parent dialog, annotated with IsRet 
   */
  trait RetBtn extends Btn with IsRet

  trait Logging:
    def report(any:Any):Unit
    def audit(any: Any):Unit
    def fatal( any: Any):Unit
    def error( any: Any):Unit
    def warn( any: Any):Unit
    def info(any: Any): Unit
    def debug( any: Any):Unit
    def trace( any: Any):Unit
  end Logging


  trait LogDelegate extends Logging:
    def logger:Logging
    final def report(any: Any):Unit = logger.report(any)
    final def audit(any: Any):Unit = logger.audit(any)
    final def fatal( any: Any):Unit = logger.fatal(any)
    final def error( any: Any):Unit = logger.error(any)
    final def warn( any: Any):Unit = logger.warn(any)
    final def info(any: Any): Unit = logger.info(any)
    final def debug( any: Any):Unit = logger.debug(any)
    final def trace( any: Any):Unit = logger.trace(any)
  end LogDelegate


  /**
   * an extremely simple logger that writes to the console
   */
  trait LogSimple extends Logging:
    def impl(any:Any):Unit = println(any)
    def report(any: Any):Unit = impl(any)

    /**
     * log regardless of any level
     * @param any
     */
    def audit(any: Any):Unit = impl(s"audit: $any")
    def fatal( any: Any):Unit = impl(s"fatal: $any")
    def error( any: Any):Unit = impl(s"error: $any")
    def warn( any: Any):Unit = impl(s"warn: $any")
    def info(any: Any): Unit = impl(s"info: $any")
    def debug( any: Any):Unit = impl(s"debug: $any")
    def trace( any: Any):Unit = impl(s"trace: $any")
  end LogSimple

