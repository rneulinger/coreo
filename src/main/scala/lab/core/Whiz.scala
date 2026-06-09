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


/**
 * Represents a wizard-like UI flow consisting of a leftmost step,
 * a rightmost step and optional center steps.
 *
 * For buttons such as “Finish” / “Cancel,” several variations are appropriate in English depending on the context. The most common are:
 * Standard UI labels
 *
 * Finish → ✅ Finish / Complete
 * Cancel → ❌ Cancel
 *
 * Alternatives (depending on context)
 *
 *
 * “Finish”
 *
 * Done (casual / short, very common in apps)
 * Submit (for forms)
 * Confirm (for confirmations)
 *
 *
 “Cancel”

 Abort (technical term, rarely used in UI)
 Dismiss (e.g., in dialogs)
 Close (when simply closing a window)



 Recommendation (most universal)
 Plain TextFinish / CancelShow more lines
 or more modern (especially in apps):
 Plain TextDone / Cancel

 For a wizard or multi-step dialog, it’s a good idea to name the buttons consistently and in line with UX standards:
 ✅ Recommended standard labels

 Previous → for “back”
 Next → for “next”
 Finish / Done → for “finish”
 Cancel → for “cancel”

 💡 Typical Setup (Best Practice)
 Plain Text[Cancel]        [Previous] [Next]Show more rows
 On the last page:
 Plain Text[Cancel]        [Previous] [Finish]Show more rows

 🔁 Alternatives (depending on the application's style)

 Previous

 Back (more common than “Previous” in modern UI)

 Finish

 Done (a bit more casual)
 Submit (when sending data)


 ✅ My recommendation (modern & very common)

 Plain TextBack / Next / Finish / Cancel``Weitere Zeilen anzeigen
 Das ist heute in den meisten Apps der Standard.

 *
 * @param left    the first (leftmost) step of the wizard
 * @param right   the final (rightmost) step of the wizard
 * @param centers optional intermediate steps between left and right
 * @param app     implicit application context
 */

class _Whiz(left: _Whiz.Left, right: _Whiz.Right, centers: _Whiz.Center*)(using app: _App)


/**
 * Companion object containing interfaces for the different
 * wizard step types.
 */
object _Whiz:

  /**
   * can abbort the wizzard
   */
  trait HasDiscard:
    /**
     * Rolls back or cancels the wizard.
     *
     * @return an action that resets or aborts the wizard flow
     */
    def cancel(): _Action

  /**
   * can go to next step
   */
  trait HasNext:
    /**
     * Advances the wizard to the next step.
     *
     * @return an action that moves to the next step
     */
    def next(): _Action

  /**
   * can go to next step
   */
  trait HasPrevious:

    /**
     * Navigates back to the previous step.
     *
     * @return an action that moves to the previous step
     */

    def back(): _Action

  /**
   * can commit the wizzard
   */
  trait HasCommit:

    /**
     * Commits the wizard result and finishes the process.
     *
     * @return an action that finalizes and persists the result
     */
    def finish(): _Action

  /**
   * Represents the first (leftmost) step in the wizard.
   *
   * This step typically allows moving forward or cancelling/resetting the flow,
   * but does not provide a "previous" navigation.
   */
  trait Left extends HasDiscard with HasNext

  /**
   * Represents the final (rightmost) step in the wizard.
   *
   * This step allows completing the wizard, navigating back,
   * or cancelling the flow.
   */

  trait Right extends HasDiscard with HasPrevious with HasCommit

  /**
   * Represents an intermediate (center) step in the wizard.
   *
   * These steps support full navigation: forward, backward,
   * and cancellation.
   */
  trait Center extends HasDiscard with HasPrevious with HasNext


/**
 * A concrete wizard implementation using standard navigation buttons:
 * Next / Previous / Ok / Cancel.
 *
 * This class wires predefined step types to the generic [[_Whiz]] abstraction.
 *
 * @param left    the first step supporting "Next" and "Cancel"
 * @param right   the final step supporting "Previous", "Ok" and "Cancel"
 * @param centers intermediate steps supporting "Previous", "Next" and "Cancel"
 * @param app     implicit application context
 */
class _NextPreviousOkCancelWhiz(
                                 left: _NextCancel,
                                 right: _PreviousOkCancel,
                                 centers: _PreviousNextCancel*
                               )(using app: _App)
  extends _Whiz(left, right, centers *)


/**
 * Wizard step representing the first (leftmost) dialog.
 *
 * Provides:
 *  - "Next" to advance to the following step
 *  - "Cancel" to abort the wizard
 *
 * This trait must be mixed into a dialog ([[ _Dlg ]]) and defines
 * the button bindings for the corresponding [[_Whiz.Left]] actions.
 */
trait _NextCancel(using app: _App) extends _Whiz.Left:
  self: _Dlg =>

  /** Button used to cancel/abort the wizard. */
  @Overload
  val Cancel = BTN()

  /** Button used to advance to the next step. */
  @Overload
  val Next = BTN()

  /**
   * Discards the wizard flow.
   *
   * @return the action bound to the "Cancel" button
   */
  def cancel() = Cancel

  /**
   * Advances to the next step.
   *
   * @return the action bound to the "Next" button
   */
  def next() = Next


/**
 * Wizard step representing the final (rightmost) dialog.
 *
 * Provides:
 *  - "Previous" to navigate back
 *  - "Ok" to complete/commit the wizard
 *  - "Cancel" to abort the wizard
 *
 * Extends [[_OkCancel]] for standard Ok/Cancel handling and
 * binds them to the [[_Whiz.Right]] contract.
 */
trait _PreviousOkCancel(using app: _App)
  extends _Whiz.Right
    with _OkCancel:

  self: _Dlg =>

  /** Button used to navigate back to the previous step. */
  @Overload
  val Previous = BTN()

  /**
   * Completes and commits the wizard.
   *
   * @return the action bound to the "Ok" button
   */
  def finish() = Ok

  /**
   * Cancels the wizard flow.
   *
   * @return the action bound to the "Cancel" button
   */
  def cancel() = Cancel

  /**
   * Navigates to the previous step.
   *
   * @return the action bound to the "Previous" button
   */
  def back() = Previous


/**
 * Wizard step representing an intermediate (center) dialog.
 *
 * Provides full navigation:
 *  - "Previous" to go back
 *  - "Next" to advance
 *  - "Cancel" to abort the wizard
 *
 * This trait connects UI buttons to the [[_Whiz.Center]] navigation model.
 */
trait _PreviousNextCancel(using app: _App) extends _Whiz.Center:
  self: _Dlg =>

  /** Button used to cancel/abort the wizard. */
  @Overload
  val Cancel = BTN()

  /** Button used to advance to the next step. */
  @Overload
  val Next = BTN()

  /** Button used to navigate back to the previous step. */
  @Overload
  val Previous = BTN()

  /**
   * Cancels the wizard flow.
   *
   * @return the action bound to the "Cancel" button
   */
  def cancel() = Cancel

  /**
   * Advances to the next step.
   *
   * @return the action bound to the "Next" button
   */
  def next() = Next

  /**
   * Navigates back to the previous step.
   *
   * @return the action bound to the "Previous" button
   */
  def back() = Previous

/**
 * A wizard implementation using the navigation scheme:
 * Next / Back / Finish / Cancel.
 *
 * This variant follows a slightly more user-friendly naming convention:
 * - "Back" instead of "Previous"
 * - "Finish" (via Ok) instead of "Commit"
 *
 * @param left  the first step supporting "Next" and "Cancel"
 * @param right the final step supporting "Back", "Finish" and "Cancel"
 * @param mids  intermediate steps supporting "Back", "Next" and "Cancel"
 * @param app   implicit application context
 */
class _FinishCancelNextBackWhiz(
                                 left: _NextCancel,
                                 right: _BackCancelFinish,
                                 mids: _BackNextCancel*
                               )(using app: _App)
  extends _Whiz(left, right, mids *)


/**
 * Wizard step representing the final (rightmost) dialog.
 *
 * Provides:
 *  - "Back" to navigate to the previous step
 *  - "Finish" (mapped to Ok) to complete the wizard
 *  - "Cancel" to abort the wizard
 *
 * Extends [[_OkCancel]] to reuse standard Ok/Cancel behavior.
 */
trait _BackCancelFinish(using app: _App)
  extends _Whiz.Right
    with _OkCancel:

  self: _Dlg =>

  /** Button used to navigate back to the previous step. */
  @Overload
  val Back = BTN()

  /**
   * Completes and finalizes the wizard.
   *
   * @return the action bound to the "Ok" (Finish) button
   */
  def finish() = Ok

  /**
   * Cancels the wizard flow.
   *
   * @return the action bound to the "Cancel" button
   */
  def cancel() = Cancel

  /**
   * Navigates back to the previous step.
   *
   * @return the action bound to the "Back" button
   */
  def back() = Back


/**
 * Wizard step representing an intermediate (center) dialog.
 *
 * Provides full navigation:
 *  - "Back" to go to the previous step
 *  - "Next" to advance to the next step
 *  - "Cancel" to abort the wizard
 *
 * Uses more user-friendly naming ("Back" instead of "Previous").
 */
trait _BackNextCancel(using app: _App) extends _Whiz.Center:
  self: _Dlg =>

  /** Button used to cancel/abort the wizard. */
  @Overload
  val Cancel = BTN()

  /** Button used to advance to the next step. */
  @Overload
  val Next = BTN()

  /** Button used to navigate back to the previous step. */
  @Overload
  val Back = BTN()

  /**
   * Cancels the wizard flow.
   *
   * @return the action bound to the "Cancel" button
   */
  def cancel() = Cancel

  /**
   * Advances to the next step.
   *
   * @return the action bound to the "Next" button
   */
  def next() = Next

  /**
   * Navigates back to the previous step.
   *
   * @return the action bound to the "Back" button
   */
  def back() = Back