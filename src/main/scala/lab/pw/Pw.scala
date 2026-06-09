package lab.pw

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

import lab.core.*
import lab.utils.*

type Loc = Page => Locator
type Adp = Ctrl => Loc

class App extends _App:
  def dlgs = collectMembersOfType(this, classOf[_Dlg])

// playwright
class Dlg(using app: App) extends _Dlg:

  def wrap(loc: Loc): Adp = _ => loc

  def BTN(id: UiId = null): Btn = {
    id match {
      case null => Btn(null)(using this)
      case x: String => Btn(null)(using this)
    }
  }

  def BTN(loc: Loc) = Btn(wrap(loc))(using this)

  def TXT(id: String = null): Txt = {
    id match {
      case null => Txt(null)(using this)
      case x: String => Txt(null)(using this)
    }
  }

  def TXT(loc: Loc) = Txt(wrap(loc))(using this)

abstract class Ctrl(var loc: Adp)(using dlg: Dlg, app: App) extends _Ctrl:
  def click() = dlg

  def set(value: Any) = dlg

  def get(): String = ""

abstract class Data(loc: Adp)(using dlg: Dlg, app: App) extends Ctrl(loc) with _Data

abstract class Action(loc: Adp)(using dlg: Dlg, app: App) extends Ctrl(loc) with _Action

class Txt(loc: Adp)(using dlg: Dlg, app: App) extends Data(loc) with _Txt

class Btn(loc: Adp)(using dlg: Dlg, app: App) extends Action(loc) with _Btn

trait MixInPw(using app: App) extends MixIn:
  self: Dlg =>

trait OkCancel(using app: App) extends _OkCancel:
  self: Dlg =>

trait CancelNextPrevious(using app: App) extends Dlg with _PreviousNextCancel:
  self: Dlg =>

