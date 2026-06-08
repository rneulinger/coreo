package lab.core

/**
 * basic definition for implmenting a wizzard
 * contains at least two dialogs left and right and optional dialogs in the middle
 *
 * Für Buttons wie „fertigstellen“ / „abbrechen“ passen im Englischen je nach Kontext mehrere Varianten. Die gängigsten sind:
 * Standard-UI-Bezeichnungen
 *
 * Fertigstellen → ✅ Finish / Complete
 * Abbrechen → ❌ Cancel
 *
 * Alternativen (je nach Kontext)
 *
 *
 * „Fertigstellen“
 *
 * Done (locker / kurz, sehr häufig in Apps)
 * Submit (bei Formularen)
 * Confirm (bei Bestätigungen)
 *
 *
 *
 * „Abbrechen“
 *
 * Abort (technisch, eher selten in UI)
 * Dismiss (z. B. bei Dialogen)
 * Close (wenn nur geschlossen wird)
 *
 *
 *
 * Empfehlung (am universellsten)
 * Plain TextFinish / CancelWeitere Zeilen anzeigen
 * oder moderner (vor allem in Apps):
 * Plain TextDone / Cancel
 *
 * Für einen Wizard / mehrstufigen Dialog lohnt es sich, die Buttons konsistent und typisch für UX-Standards zu benennen:
 * ✅ Empfohlene Standard-Bezeichnungen
 *
 * Previous → für „zurück“
 * Next → für „weiter“
 * Finish / Done → für „fertigstellen“
 * Cancel → für „abbrechen“
 *
 *
 * 💡 Typisches Setup (Best Practice)
 * Plain Text[Cancel]        [Previous] [Next]Weitere Zeilen anzeigen
 * Auf der letzten Seite:
 * Plain Text[Cancel]        [Previous] [Finish]Weitere Zeilen anzeigen
 *
 * 🔁 Alternativen (je nach Stil der Anwendung)
 *
 * Previous
 *
 * Back (häufiger als „Previous“ in moderner UI)
 *
 *
 * Finish
 *
 * Done (etwas lockerer)
 * Submit (wenn Daten abgeschickt werden)
 *
 *
 *
 *
 * ✅ Meine Empfehlung (modern & sehr üblich)
 * Plain TextBack / Next / Finish / Cancel``Weitere Zeilen anzeigen
 * Das ist heute in den meisten Apps der Standard.
 * Wenn du magst, kann ich dir auch gleich eine komplette UX-Konvention (inkl. Disabled-States, Reihenfolge, etc.) zusammenstellen 👍
 */

class _Whiz(left: _Whiz.Left, right: _Whiz.Right, centers: _Whiz.Center*)(using app: _App)

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
    def rollback(): _Action

    def next(): _Action

  trait Right:

    def rollback(): _Action

    def commit(): _Action

    def previous(): _Action

  trait Center:
    def rollback(): _Action

    def next(): _Action

    def previous(): _Action


/**
 * Wizzar implemented as Ok, Cancel Previous, Next
 *
 * @param left
 * @param right
 * @param mid
 * @param app
 */
class _NextPreviousOkCancelWhiz(left: _NextCancel, right: _PreviousOkCancel, centers: _PreviousNextCancel*)(using app: _App) extends _Whiz(left, right, centers *)

trait _NextCancel(using app: _App) extends _Whiz.Left:
  self: _Dlg =>
  @Overload
  val Cancel = BTN()
  @Overload
  val Next = BTN()

  def rollback() = Cancel

  def next() = Next

trait _PreviousOkCancel(using app: _App) extends _Whiz.Right with _OkCancel:
  self: _Dlg =>

  @Overload
  val Previous = BTN()

  def commit() = Ok

  def rollback() = Cancel

  def previous() = Previous

trait _PreviousNextCancel(using app: _App) extends _Whiz.Center:
  self: _Dlg =>
  @Overload
  val Cancel = BTN()
  @Overload
  val Next = BTN()
  @Overload
  val Previous = BTN()

  def rollback() = Cancel

  def next() = Next

  def previous() = Previous


class _FinishCancelNextBackWhiz(left: _NextCancel, right: _BackCancelFinish, mids: _BackNextCancel*)(using app: _App) extends _Whiz(left, right, mids *)

trait _BackCancelFinish(using app: _App) extends _Whiz.Right with _OkCancel:
  self: _Dlg =>

  @Overload
  val Back = BTN()

  def commit() = Ok

  def rollback() = Cancel

  def previous() = Back

trait _BackNextCancel(using app: _App) extends _Whiz.Center:
  self: _Dlg =>
  @Overload
  val Cancel = BTN()
  @Overload
  val Next = BTN()
  @Overload
  val Back = BTN()

  def rollback() = Cancel

  def next() = Next

  def previous() = Back

