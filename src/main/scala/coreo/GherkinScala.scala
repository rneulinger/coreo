package coreo

import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import io.cucumber.datatable.DataTable
import io.cucumber.scala.{EN, PendingException, ScalaDsl}

import scala.jdk.CollectionConverters.*

trait GherkinScala
  extends ScalaDsl
    with EN {

  val I = "I "

  def getClient(name: String = I): PwApp

  def `/`: PwApp = getClient()

  def get(dlg: String, table: DataTable, client: String = I): Unit = {
    val cc = Defs.mkCamelCase(dlg)
    println(s"$client get: $cc")
    println(/.frms.keySet.contains(dlg))
    // /.set(string)
  }
  /*
    When("get:") {
      (data: DataTable) =>
    }
  
    When("{word} get:") {
      (client: String, data: DataTable) =>
    }
  
    When("get: {string}") {
      (dlg: String, data: DataTable) =>
        get(dlg, data)
    }
  
    When("{word} get: {string}") {
      (client: String, dlg: String, table: DataTable) =>
        get(dlg, table, client)
    }
  
    def set(dlg: String, data: DataTable, client: String = I): Unit = {
      val cc = Defs.mkCamelCase(dlg)
      onto(cc)
      println(s"$client set: $cc")
      println(/.frms.keySet.contains(dlg))
  
      val header = data.row(0).asScala.toList
      //assert(header == List("name","generic", "typ"))
      val rows = data.asLists().asScala.toList.drop(1)
      val atoms = /.currentWin.atoms
      for (row <- rows) {
        println(row.asScala.toList)
        val name = row.asScala.toList.head
        val value = row.asScala.toList.tail.head
  
        val hits = /.currentWin.findAtoms(Defs.mkCamelCase(name))
  
        hits.size match {
          case 0 => throw Exception(s"name $name not found")
          case 1 => hits.head.set(value)
          case _ => throw Exception(s"name $name is ambiguous $hits")
        }
      }
    }
  
    When("set:") {
      (data: DataTable) =>
    }
    When("{word} set:") {
      (client: String, data: DataTable) =>
    }
    When("set: {string}") {
      (dlg: String, data: DataTable) =>
        set(dlg, data)
    }
  
    When("{word} set: {string}") {
      (client: String, dlg: String, data: DataTable) =>
        set(dlg, data, client)
    }
  
    def verify(dlg: String, table: DataTable, client: String = I): Unit = {
      val cc = Defs.mkCamelCase(dlg)
      println(s"$client chk: $cc")
      println(/.frms.keySet.contains(dlg))
      // /.set(string)
    }
  
    Then("verify:") {
      (table: DataTable) =>
    }
  
    Then("{word} verify:") {
      (client: String, table: DataTable) =>
    }
  
    Then("verify: {string}") {
      (dlg: String, table: DataTable) =>
    }
    Then("{word} verify: {string}") {
      (client: String, dlg: String, table: DataTable) =>
    }
  
    def act(dest: String, table: DataTable, who: String = I): Unit = {
      val cc = Defs.mkCamelCase(dest)
      println(s"$who act: $cc")
      println(/.frms.keySet.contains(dest))
      // /.set(string)
    }
  
    When("act:") {
      (data: DataTable) =>
    }
    When("{word} act:") {
      (client: String, data: DataTable) =>
    }
    When("act: {string}") {
      (dlg: String, data: DataTable) =>
        act(dlg, data)
    }
    When("{word} act: {string}") {
      (client: String, dlg: String, data: DataTable) =>
        act(dlg, data, client)
    }
  
    def filter(dlg: String, data: DataTable, client: String = I): Unit = {
      val cc = Defs.mkCamelCase(dlg)
      println(s"$client filter: $cc")
      println(/.frms.keySet.contains(dlg))
      // /.set(string)
    }
  
    Then("filter:") {
      (data: DataTable) =>
    }
    Then("{word} filter:") {
      (client: String, data: DataTable) =>
    }
    Then("filter: {string}") {
      (dlg: String, data: DataTable) =>
    }
    Then("{word} filter: {string}") {
      (client: String, dlg: String, data: DataTable) =>
    }
  
    /**
     *
     * @param dlg
     * @param client
     */
    def goto(dlg: String, client: String = I): Unit = {
      println(s"$client goto: $dlg")
      /.goto(dlg)
    }
  
    When("goto: {string}") { (dlg: String) => goto(dlg) }
    When("{word} goto: {string}") { (client: String, dlg: String) => goto(dlg, client) }
  
    /**
     *
     * @param dlg
     * @param client
     */
    def onto(dlg: String, client: String = I): Unit = {
      println(s"$client onto: $dlg")
      /.onto(dlg)
    }
  
    When("onto: {string}") { (dlg: String) => onto(dlg) }
    When("{word} onto: {string}") { (client: String, dlg: String) => onto(dlg, client) }
    When("in: {string}") { (dlg: String) => onto(dlg) }
    When("{word} in: {string}") { (client: String, dlg: String) => onto(dlg, client) }
  
  
    /**
     *
     * @param dlg
     * @param data
     * @param client
     */
    def add(dlg: String, data: DataTable, client: String = I): Unit = {
      val cc = Defs.mkCamelCase(dlg)
      println(s"$client add: $cc")
      println(/.frms.keySet.contains(dlg))
      // /.add(string)
    }
  
    When("add: {string}") {
      (dlg: String, data: DataTable) => onto(dlg); add(dlg, data)
    }
    When("{word} add: {string}") {
      (client: String, dlg: String, data: DataTable) =>
        onto(dlg); add(dlg, data, client)
    }
  
    def edit(dlg: String, data: DataTable, client: String = I): Unit = {
      val cc = Defs.mkCamelCase(dlg)
      println(s"$client add: $cc")
      println(/.frms.keySet.contains(dlg))
      // /.edit(string)
    }
  
    When("edit: {string}") {
      (dlg: String, data: DataTable) =>
        onto(dlg); edit(dlg, data)
    }
    When("{word} edit: {string}") {
      (client: String, dlg: String, table: DataTable) =>
        onto(dlg); edit(dlg, table, client)
    }
  
    def wait(secs: Double, client: String = I): Unit = {
      val ms = (secs * 1000).toInt
      print(s"I wait $ms ms ....")
      Thread.sleep(ms)
      println(" done")
    }
  
    When("wait {double}") {
      (secs: Double) =>
        wait(secs)
    }
    When("{word} wait {double}") {
      (client: String, secs: Double) =>
        wait(secs, client)
    }
  
    def set(dlg: String, value: String, client: String): Unit = {
      val hit = /.currentWin.findAtom(dlg)
      hit match {
        case None =>
          println(s"current frame does not contain: $dlg")
          println("->" + /.currentWin.atoms.keySet)
        case Some(atom) =>
          println(s"I set ${atom.cleanName} = $value in ${/.currentWin.myType}")
          atom.set(value)
      }
    }
  
    When("set {string} = {string}") {
      (obj: String, value: String) =>
        set(obj, value, I)
    }
    When("{word} set {string} = {string}") {
      (client: String, obj: String, value: String) =>
        set(obj, value, client)
    }
  
  
    def chk(obj: String, value: String): Unit = set(obj, value, I)
  
    Then("chk {string} == {string}") {
      (obj: String, value: String) =>
        onto(obj)
    }
    Then("{word} chk {string} == {string}") {
      (client: String, obj: String, value: String) =>
        onto(obj)
    }
  
    def get(obj: String, value: String): Unit = set(obj, value, I)
  
    When("get {string} == {string}") {
      (obj: String, value: String) =>
        onto(obj)
    }
    When("{word} get {string} -> {string}") {
      (client: String, obj: String, value: String) =>
        onto(obj)
    }
  
    def click(obj: String, client: String = I) = {
      val hit = /.currentWin.findAtom(obj)
      hit match {
        case None =>
          println(s"current frame does not contain: $obj")
          println("->" + /.currentWin.atoms.keySet)
        case Some(atom) =>
          println(s"I click on ${atom.cleanName} in ${/.currentWin.myType}")
          atom.click
      }
    }
  
    When("{word} click {string}") {
      (client: String, obj: String) =>
        click(obj, client)
    }
    When("click {string}") {
      (obj: String) =>
        click(obj)
    }
  
    def clickFail(obj: String, client: String = "") = {
      val hit = /.currentWin.findAtom(obj)
      hit match {
        case None =>
          println(s"current frame does not contain: $obj")
          println("->" + /.currentWin.atoms.keySet)
        case Some(atom) =>
          println(s"I click on ${atom.cleanName} in ${/.currentWin.myType}")
          atom.clickFail
      }
    }
  
    When("clickFail {string}") {
      (obj: String) =>
        clickFail(obj)
    }
  
    When("findText {string}") {
      (txt: String) =>
        assertThat(/.pg.getByText(txt)).isVisible()
    }
  
    When("clickText {string}") {
      (txt: String) =>
        /.pg.getByText(txt, new Page.GetByTextOptions().setExact(true)).click()
    }
  
    When("check {string}") {
      (obj: String) =>
        val hit = /.currentWin.findAtom(obj)
        hit match {
          case None =>
            println(s"current frame does not contain: $obj")
            println("->" + /.currentWin.atoms.keySet)
          case Some(atom) =>
            println(s"I click on ${atom.cleanName} in ${/.currentWin.myType}")
            atom.check
        }
    }
  
    When("uncheck {string}") {
      (obj: String) =>
        val hit = /.currentWin.findAtom(obj)
        hit match {
          case None =>
            println(s"current frame does not contain: $obj")
            println("->" + /.currentWin.atoms.keySet)
          case Some(atom) =>
            println(s"I click on ${atom.cleanName} in ${/.currentWin.myType}")
            atom.uncheck
        }
    }
  
    //Given("set:") { (table: DataTable) =>
    //      val users: List[Map[String, String]] = table.asMaps(classOf[String], classOf[String])
    //
    //      users.foreach { row =>
    //        println(s"Name: ${row("name")}, Age: ${row("age")}")
    //      }
    // }
  
    When("back:") {
      () =>
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
  
  
    Then("^([A-Za-z0-9_]+) chk '([^']*)' (=|==|!=|<|<=|>=|>|!~|=~) '([^']*)'$") {
      (client: String, obj: String, op: String, value: String) =>
  
    }
    Then("^chk '([^']*)' (=|==|!=|<|<=|>=|>|!~|=~) '([^']*)'$") {
      (obj: String, op: String, value: String) =>
    }
  
    Then("([A-Za-z0-9_]+) ^chk '([^']*)' (enabled|disabled|visible|hidden|focused|unfocused|checked|unchecked|selected|deselected|indeterminate)$") {
      (client: String, obj: String, prop: String) =>
    }
    Then("^chk '([^']*)' (isEnabled|isDisabled|isVisible|isHidden|hasFocus)$") {
      (obj: String, prop: String) =>
    }
  
    //// special keywords
  
    Given("^([!=_][!=_]) (.+)$") { (pred: String, cmd: String) =>
      println(s"Smart given $pred $cmd")
    }
  
    def setRandom(obj: String, value: String, who: String): Unit = {
      val hit = /.currentWin.findAtom(obj)
      hit match {
        case None =>
          println(s"current frame does not contain: $obj")
          println("->" + /.currentWin.atoms.keySet)
        case Some(atom) =>
          val rand = atom.gen(value)
          println(s"I set ${atom.cleanName} = $rand in ${/.currentWin.myType}")
          atom.random(value)
      }
    }
  
    When("set {string} =% {string}") {
      (obj: String, value: String) =>
        setRandom(obj, value, I)
    }
    When("{word} set {string} =% {string}") {
      (client: String, obj: String, value: String) =>
        setRandom(obj, value, client)
    }
  
    def pause(client: String = I): Unit = {
      /.pause()
    }
  
    When("pause") { () =>
      pause(I)
    }
    When("{word} pause") { (client: String) =>
      pause(client)
    }
  
   */
}

class GherkinScalaImpl extends GherkinScala {

  override def getClient(name: String): PwApp = ???
}
