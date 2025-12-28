package coreo


abstract class FRM(own: CanOwn, typ: String = "") extends WIN(own, typ) {
  def goto: FRM = {
    own.onto(this)
    openUrl(path)
    Thread.sleep(1000)
    this
  }

}
