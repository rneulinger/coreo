package lab.pw

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}
import coreo.TBD
import lab.core.*
import lab.utils.*

type Loc = Page => Locator
type Adp = Ctrl => Loc

class App extends _App:
  final val Unknown = new Dlg(using this){}

// playwright
class Dlg(using app: App) extends _Dlg:

  def wrap(loc: Loc): Adp = _ => loc

  def BTN(loc: Loc) = Btn(wrap(loc))(using this)
  def BTN(id: UiId = null): Btn = {
    id match {
      case null => Btn(null)(using this)
      case x: String => mkBtnById( id)
    }
  }

  protected def mkBtnById(id: String): Btn = ???;

  def RET(id: UiId = null): RetBtn = {
    id match {
      case null => RetBtn(null)(using this)
      case x: String => mkRetById( id)
    }
  }
  protected def mkRetById( id: String):RetBtn = ???;

  def SUB(id: UiId = null): SubBtn = {
    id match {
      case null => SubBtn(null)(using this)
      case x: String => mkSubById( id)
    }
  }

  protected def mkSubById( id: String):SubBtn = ???;

  def BACK(id: UiId = null): BackBtn = {
    id match {
      case null => BackBtn(null)(using this)
      case x: String => mkBackById(id)
    }
  }

  protected def mkBackById(id: String): BackBtn = ???;

  def TO(id: UiId = null): ToBtn = {
    id match {
      case null => ToBtn(null)(using this)
      case x: String => mkToById(id)
    }
  }

  protected def mkToById(id: String): ToBtn = ???;

  def TXT(loc: Loc) = Txt(wrap(loc))(using this)
  def TXT(id: String = null): Txt = {
    id match {
      case null => Txt(null)(using this)
      case x: String => mkTxtById( id)
    }
  }
  protected def mkTxtById(id: String): Txt = ???;


abstract class Ctrl(var loc: Adp)(using dlg: Dlg, app: App) extends _Ctrl:
  def click() = dlg

  def set(value: Any) = dlg

  def get(): String = ""

abstract class Data(loc: Adp)(using dlg: Dlg, app: App) extends Ctrl(loc) with _Data

abstract class Action(loc: Adp)(using dlg: Dlg, app: App) extends Ctrl(loc) with _Action

class Txt(loc: Adp)(using dlg: Dlg, app: App) extends Data(loc) with _Txt

@TBD
class Btn(loc: Adp)(using dlg: Dlg, app: App) extends Action(loc) with _Btn
class SubBtn(loc: Adp)(using dlg: Dlg, app: App) extends Btn(loc) with _SubBtn
class RetBtn(loc: Adp)(using dlg: Dlg, app: App) extends Btn(loc) with _RetBtn
class BackBtn(loc: Adp)(using dlg: Dlg, app: App) extends Btn(loc) with _BackBtn
class ToBtn(loc: Adp)(using dlg: Dlg, app: App) extends Btn(loc) with _ToBtn

trait MixIn(using app: App) extends _MixIn:
  self: Dlg =>

trait OkCancel(using app: App) extends _OkCancel:
  self: Dlg =>

trait CancelNextPrevious(using app: App) extends Dlg with PreviousNextCancel:
  self: Dlg =>

