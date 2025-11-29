package steps

import io.cucumber.scala.{EN, PendingException, ScalaDsl}
import io.cucumber.datatable.DataTable
import coreo.*

class StepDefinitions extends ScalaDsl with EN {
  var `/`: practice.Practice = _
  val I = "I "

  def get(dest: String, table: DataTable, who: String = I): Unit = {
    val cc = Defs.mkCamelCase(dest)
    println(s"$who get: $cc")
    println(/.frms.keySet.contains(dest))
    // /.set(string)
  }

  When("get:") { (table: DataTable) =>
  }
  When("{word} get:") { (who: String, table: DataTable) =>
  }
  When("get: {string}") { (obj: String, table: DataTable) =>
    get(obj, table)
  }
  When("{word} get: {string}") { (who: String, obj: String, table: DataTable) =>
    get(obj, table, who)
  }

  def set(dest: String, table: DataTable, who: String = I): Unit = {
    val cc = Defs.mkCamelCase(dest)
    println(s"$who set: $cc")
    println(/.frms.keySet.contains(dest))
    // /.set(string)
  }

  When("set:") { (table: DataTable) =>
  }
  When("{word} set:") { (who: String, table: DataTable) =>
  }
  When("set: {string}") { (obj: String, table: DataTable) =>
    set(obj, table)
  }
  When("{word} set: {string}") { (who: String, obj: String, table: DataTable) =>
    set(obj, table, who)
  }

  def chk(dest: String, table: DataTable, who: String = I): Unit = {
    val cc = Defs.mkCamelCase(dest)
    println(s"$who chk: $cc")
    println(/.frms.keySet.contains(dest))
    // /.set(string)
  }

  Then("chk:") { (table: DataTable) =>
  }
  Then("{word} chk:") { (who: String, table: DataTable) =>
  }
  Then("chk: {string}") { (obj: String, table: DataTable) =>
  }
  Then("{word} chk: {string}") { (who: String, obj: String, table: DataTable) =>
  }

  def act(dest: String, table: DataTable, who: String = I): Unit = {
    val cc = Defs.mkCamelCase(dest)
    println(s"$who act: $cc")
    println(/.frms.keySet.contains(dest))
    // /.set(string)
  }

  When("act:") { (table: DataTable) =>
  }
  When("{word} act:") { (who: String, table: DataTable) =>
  }
  When("act: {string}") { (obj: String, table: DataTable) =>
    act(obj, table)
  }
  When("{word} act: {string}") { (who: String, obj: String, table: DataTable) =>
    act(obj, table, who)
  }

  def filter(dest: String, table: DataTable, who: String = I): Unit = {
    val cc = Defs.mkCamelCase(dest)
    println(s"$who filter: $cc")
    println(/.frms.keySet.contains(dest))
    // /.set(string)
  }

  Then("filter:") { (table: DataTable) =>
  }
  Then("{word} filter:") { (who: String, table: DataTable) =>
  }
  Then("filter: {string}") { (obj: String, table: DataTable) =>
  }
  Then("{word} filter: {string}") { (who: String, obj: String, table: DataTable) =>
  }

  /**
   *
   * @param dest
   * @param who
   */
  def goto(dest: String, who: String = I): Unit = {
    val cc = Defs.mkCamelCase(dest)
    println(s"$who goto: $cc")
    /.goto(cc)
  }

  When("goto: {string}") { (obj: String) =>
    goto(obj)
  }
  When("{word} goto: {string}") { (who: String, obj: String) =>
    goto(obj, who)
  }

  /**
   *
   * @param dest
   * @param who
   */
  def onto(dest: String, who: String = I): Unit = {
    val cc = Defs.mkCamelCase(dest)
    println(s"$who onto: $cc")
    println(/.frms.keySet.contains(dest))
    /.onto(cc)
  }

  When("onto: {string}") { (obj: String) =>
    onto(obj)
  }
  When("{word} onto: {string}") { (who: String, obj: String) =>
    onto(obj, who)
  }
  When("in: {string}") { (obj: String) =>
    onto(obj)
  }
  When("{word} in: {string}") { (who: String, obj: String) =>
    onto(obj, who)
  }


  /**
   *
   * @param dest
   * @param table
   * @param who
   */
  def add(dest: String, table: DataTable, who: String = I): Unit = {
    val cc = Defs.mkCamelCase(dest)
    println(s"$who add: $cc")
    println(/.frms.keySet.contains(dest))
    // /.add(string)
  }

  When("add: {string}") { (obj: String, table: DataTable) =>
    onto(obj); add(obj, table)
  }
  When("{word} add: {string}") { (who: String, table: DataTable, obj: String) =>
    onto(obj); add(obj, table, who)
  }

  def edit(dest: String, table: DataTable, who: String = I): Unit = {
    val cc = Defs.mkCamelCase(dest)
    println(s"$who add: $cc")
    println(/.frms.keySet.contains(dest))
    // /.edit(string)
  }

  When("edit: {string}") { (obj: String, table: DataTable) =>
    onto(obj); edit(obj, table)
  }
  When("{word} edit: {string}") { (who: String, table: DataTable, obj: String) =>
    onto(obj); edit(obj, table, who)
  }

  def wait(secs: Double, dest: String = I): Unit = {
    val ms = (secs * 1000).toInt
    print(s"I wait $ms ms ....")
    Thread.sleep(ms)
    println(" done")
  }

  When("wait {double}") { (secs: Double) =>
    wait(secs)
  }
  When("{word} wait {double}") { (secs: Double, who: String) =>
    wait(secs, who)
  }

  def set(obj: String, value: String, who: String): Unit = {
    val hit = /.currentFrm.find(obj)
    hit match {
      case None =>
        println(s"current frame does not contain: $obj")
        println("->" + /.currentFrm.atoms.keySet)
      case Some(atom) =>
        println(s"I set ${atom.cleanName} = $value in ${/.currentFrm.myType}")
        atom.set(value)
    }
  }

  When("set {string} = {string}") { (obj: String, value: String) =>
    set( obj, value, I)
  }
  When("{word} set {string} = {string}") { (who: String, obj: String, value: String) =>
    set( obj, value, who)
  }


  def chk(obj: String, value: String): Unit = set(obj, value, I)

  Then("chk {string} == {string}") { (obj: String, value: String) =>
    onto(obj)
  }
  Then("{word} chk {string} == {string}") { (who: String, obj: String, value: String) =>
    onto(obj)
  }

  def get(obj: String, value: String): Unit = set(obj, value, I)

  When("get {string} == {string}") { (obj: String, value: String) =>
    onto(obj)
  }
  When("{word} get {string} -> {string}") { (who: String, obj: String, value: String) =>
    onto(obj)
  }

  def click(obj: String, who: String = "") = {
    val hit = /.currentFrm.find(obj)
    hit match {
      case None =>
        println(s"current frame does not contain: $obj")
        println("->" + /.currentFrm.atoms.keySet)
      case Some(atom) =>
        println(s"I click on ${atom.cleanName} in ${/.currentFrm.myType}")
        atom.click
    }
  }

  When("{word} click {string}") { (who: String, obj: String) =>
    click(obj)
  }
  When("click {string}") { (obj: String) =>
    click(obj)
  }
  //Given("set:") { (table: DataTable) =>
  //      val users: List[Map[String, String]] = table.asMaps(classOf[String], classOf[String])
  //
  //      users.foreach { row =>
  //        println(s"Name: ${row("name")}, Age: ${row("age")}")
  //      }
  // }

  Given("back:") { () =>
  }
  //Given("get:") { (table: DataTable) =>
  //      val users: List[Map[String, String]] = table.asMaps(classOf[String], classOf[String])
  //
  //      users.foreach { row =>
  //        println(s"Name: ${row("name")}, Age: ${row("age")}")
  //      }
  //}

  //Given("chk:") { (table: DataTable) =>
  //      val users: List[Map[String, String]] = table.asMaps(classOf[String], classOf[String])
  //
  //      users.foreach { row =>
  //        println(s"Name: ${row("name")}, Age: ${row("age")}")
  //      }
  //}


  //Given("act:") { (table: DataTable) =>
  //}

  Then("^([A-Za-z0-9_]+) chk '([^']*)' (=|==|!=|<|<=|>=|>|!~|=~) '([^']*)'$") { (who: String, obj: String, op: String, value: String) =>

  }
  Then("^chk '([^']*)' (=|==|!=|<|<=|>=|>|!~|=~) '([^']*)'$") { (obj: String, op: String, value: String) =>

  }

  Then("([A-Za-z0-9_]+) ^chk '([^']*)' (enabled|disabled|visible|hidden|focused|unfocused|checked|unchecked|selected|deselected|indeterminate)$") { (who: String, obj: String, prop: String) =>
  }
  Then("^chk '([^']*)' (isEnabled|isDisabled|isVisible|isHidden|hasFocus)$") { (obj: String, prop: String) =>

  }

  //// special keywords

  Given("^[!=_][!=_] (.+)$") { () =>

  }

  def setRandom(obj: String, value: String, who: String): Unit = {
    val hit = /.currentFrm.find(obj)
    hit match {
      case None =>
        println(s"current frame does not contain: $obj")
        println("->" + /.currentFrm.atoms.keySet)
      case Some(atom) =>
        val rand = atom.gen(value)
        println(s"I set ${atom.cleanName} = $rand in ${/.currentFrm.myType}")
        atom.random(value)
    }
  }

  When("set {string} =@ {string}") { (obj: String, value: String) =>
    setRandom(obj, value, I)
  }
  When("{word} set {string} =@ {string}") { (who: String, obj: String, value: String) =>
    setRandom(obj, value, who)
  }


  Before { scenario =>
    `/` = practice.Practice()
    println(s"Setup before scenario: ${scenario.getName}")
  }

  After { scenario =>
    println(s"Teardown after scenario: ${scenario.getName}")
    /.pg.close()
    /.browser.close()
    / = null
  }
}

