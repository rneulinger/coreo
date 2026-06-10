package lab.core

trait _MixIn(using app: _App):
  self: _Dlg =>

trait _OkCancel(using app: _App):
  self: _Dlg =>
  val Ok = BTN()
  val Cancel = BTN()

trait _Commit(using app: _App):
  self: _Dlg =>
  @Ret
  val Yes = BTN()
  val No = BTN()
  val Msg = TXT()

trait _Crud:
  def create(): _Action

  def read(): _Action

  def update(): _Action

  def delete(): _Action

