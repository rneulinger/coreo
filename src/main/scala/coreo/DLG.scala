package coreo


import com.microsoft.playwright.*

abstract class DLG(own: CanOwn, typ: String = "") extends WIN(own, typ) {
  def path: String = ""
}
