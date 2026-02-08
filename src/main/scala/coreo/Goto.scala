package coreo

trait Goto extends MixIn {
  self: WIN =>

  def path: String

  def goto: Goto = {
    own.onto(this)
    openUrl(path)
    Thread.sleep(1000)
    this
  }

}
