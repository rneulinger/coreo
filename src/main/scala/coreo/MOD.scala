package coreo

/**
 * Modal dialog
 *
 * @param own owner of this dialog
 * @param ret return target
 * @param typ
 */
abstract class MOD(own: CanOwn, ret: Static, typ: String = "") extends WIN(own, typ)
  with bricks.CancelFinish {

  Cancel.target = ret // discard -> back to PTT setting
  Finish.target = ret // commit -> back to PTT setting content change, one item added
}
