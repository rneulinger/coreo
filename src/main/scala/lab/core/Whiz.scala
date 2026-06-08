package lab.core

import coreo.bricks.OkCancel

/**
 * basic definition for implmenting a wizzard
 * contains at least two dialogs left and right and optional dialogs in the middle
 */
trait _Whiz(left: _Whiz.Left, right: _Whiz.Right, mid: _Whiz.Mid*)(using app: _App)

/**
 * interfaces
 */
object _Whiz:

  /**
   * Leftmost dialog of the wizzard
   */
  trait Left:
    /**
     * @return
     */
    def cancel(): _Action

    def next(): _Action

  trait Right:

    def cancel(): _Action

    def ok(): _Action

    def previous(): _Action

  trait Mid:
    def cancel(): _Action

    def next(): _Action

    def previous(): _Action


/**
 * Wizzar implemented as Ok, Cancel Previous, Next
 * @param left
 * @param right
 * @param mid
 * @param app
 */
class _OkCancelNextPreviousWhiz( left:_CancelNext, right:_OkCancelPrevious, mid:_CancelNextPrevious*  )(using app: _App) extends _Whiz(left,right, mid*)

trait _CancelNext(using app: _App) extends _Whiz.Left:
  self: _Dlg =>
  @Overload
  val Cancel = BTN()
  @Overload
  val Next = BTN()

  def cancel() = Cancel

  def next() = Next

trait _OkCancelPrevious(using app: _App) extends _Whiz.Right with _OkCancel:
  self: _Dlg =>

  @Overload
  val Previous = BTN()

  def ok() = Ok
  def cancel() = Cancel
  def previous() = Previous

trait _CancelNextPrevious(using app: _App) extends _Whiz.Mid:
  self: _Dlg =>
  @Overload
  val Cancel = BTN()
  @Overload
  val Next = BTN()
  @Overload
  val Previous = BTN()

  def cancel() = Cancel

  def next() = Next

  def previous() = Previous


