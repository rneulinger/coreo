package lab.core

import coreo.Return

trait _MixIn(using app: _App):
  self: _Dlg =>

trait _OkCancel(using app: _App):
  self: _Dlg =>
  val Ok = RET()
  val Cancel = RET()

trait _Commit(using app: _App):
  self: _Dlg =>
  @Return
  val Yes = RET()
  val No = RET()
  val Msg = TXT()

trait _Crud:
  def create(): _Action

  def read(): _Action

  def update(): _Action

  def delete(): _Action

