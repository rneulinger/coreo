package coreo
abstract class Static {
  final def simple:String = {
    val res = getClass.getSimpleName
    assert ( res.endsWith("$"))
    res.dropRight(1)
  }
  final def name:String = {
    val res = getClass.getName
    assert(res.endsWith("$"))
    res.dropRight(1)
  }

  def path:String = ""

}
