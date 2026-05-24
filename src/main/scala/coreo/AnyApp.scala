package coreo

abstract class AnyApp extends Obj { //with CanOwn {
  def app: AnyApp = this

//  private var adoptedAtoms = List[Ctrl[?]]()
  private var adoptedDlgs = List[Dlg[?]]()

  lazy val atoms: Map[String, Ctrl[?]] = ??? // adoptedAtoms.map(a => a.fullName -> a).toMap

  lazy val (short, full, frms) = {
    val short = adoptedDlgs.map(a => a.myType -> a).toMap
    val full = adoptedDlgs.map(a => a.fullType -> a).toMap
    (short, full, short ++ full)
  }

  /**
   * short   *
   *
   * @param name of dialog
   * @return
   */
  def findWinByPath(name: String): List[Dlg[?]] = {
    adoptedDlgs.filter(_.path.contains(name))
  }
  //  def findByUiName( name:String):List[WIN] = {
  //    adoptedDlgs.filter(_.contains(name)).toList
  //  }

  def findWin(name: String): Dlg[?] = {
    val byPath = findWinByPath(name)
    if byPath.length == 1 then return byPath.head

    val res = adoptedDlgs.find(_.Self == name)
    res match {
      case None =>
        val msg =
          //${adoptedDlgs.map(_.Self).mkString("\n")}
          s"""Win not Found $name
             |""".stripMargin
        println(msg)
        throw Exception(msg)
      case Some(w) => w
    }
  }

  /**
   * variables for test execution
   */
  private var VARS = Map[String, Any]()

  final def getVars = VARS.keySet

  def setVar(key: String, value: Any): Unit = {
    val res = VARS + (key -> value)
    VARS = res
    println(s"set $key = $value")
  }

  def getVar(key: String): String = {
    VARS.get(key).toString
  }

  def getVarOrElse(key: String, default: Any): Any = {
    VARS.getOrElse(key, default)
  }

  final def adopt(dlg: Dlg[?]): Unit =
        adoptedDlgs = adoptedDlgs.appended(dlg)


  var currentDlg: Dlg[?] = new Dlg[AnyApp]() {}
  val defaultWin = currentDlg

  def findAtoms(name: String) = currentDlg.findAtoms(name)

  def findAtom(name: String) = currentDlg.findAtom(name)

  given ref: AnyApp = this
  /**
   * visit: push current view on stack, arg becomes current,
   * return: push curren tin history, pop and set current
   */
  val winStack = scala.collection.mutable.Stack[Dlg[?]]()
  val winHistory = scala.collection.mutable.Stack[Dlg[?]]()

  def onto(frm: Dlg[?]): Unit = {
    winStack.push(currentDlg)
    currentDlg = frm
    println("changed to frm:" + frm.myType)
    println(frm.dump)
  }

  def goto(name: String): Unit = {
    val clean = name.trim
    if clean.isEmpty then return

    if clean.toLowerCase() == clean then {
      println("try to find by path:" + clean)
      val hits = adoptedDlgs.filter(_.pathAbs.contains(clean))
      println(hits)
      if hits.size == 1 then
        hits.head.match {
          case frm: Dlg[?] => frm.goto()
          case _ => println(s"${hits.head} is not of type Goto")
        }
    }
    else
      val dest = Defs.mkCamelCase(clean)
      if (frms.keySet.contains(dest)) {
        val frm = frms(dest)
        frm match {
          case f: Dlg[?] => f.goto()
          case _ => throw Exception(s"goto not supported: $frm has no path")
        }
      } else {
        println("goto: cannot find frame:" + dest)
      }
  }

  def onto(name: String): Unit = {
    val clean = name.trim
    if clean.isEmpty then return

    if clean.toLowerCase() == clean then {
      println("try to find by path:" + clean)
      val hits = adoptedDlgs.filter(_.pathAbs.contains(clean))
      println(hits)
      if hits.size == 1 then
        hits.head.match {
          case frm: Dlg[?] => frm.goto()
          case _ => println(s"${hits.head} is not of type Goto")
        }
    }
    else
      val dest = Defs.mkCamelCase(clean)
      if (frms.keySet.contains(dest)) {
        onto(frms(dest))
      } else {
        println("onto: cannot find frame:" + dest)
      }
  }
  
  def back: Unit = {
    if (winStack.nonEmpty) {
      currentDlg = winStack.pop()
    } else {
      currentDlg = defaultWin
    }
  }

  final def dump(s: String = ""): Unit = {
    println(atoms)
    val hits = frms.filter(_._1.contains(s))
    for (frm <- hits) {
      println()
      frm._2.dump
    }
  }

  def mkMermaid(dlg: ADlg): String = {
    val className = if dlg.simple.trim.isEmpty then "_" else dlg.simple

    val recs = for (a <- dlg.datas) yield {
      val name = a._2.fullName
      val short = Defs.mkCamelCase(name)
      "| " + a._2.simple + " " + short
    }
    val acts = for (a <- dlg.actions) yield {
      val name = a._2.fullName
      val short = Defs.mkCamelCase(name)
      "| " + a._2.simple + " " + short
    }

    val links = findRelationsFor(dlg)

    def incoming = for (l <- links) yield {
      className + " <-- " + l._2.simple + " : " + l._1.fullName
    }

    def outgoing = for (l <- dlg.actions.filterNot(_._2.target.name == Unknown_.name)) yield {
      def last = l._2.target.name.split("\\.")
      //      last(last.length -1) + " <-- " +  win.simple + " : "+ l._2.uiName
      className + " --> " + last(last.length - 1) + " : " + l._2.fullName

    }

    s"""classDiagram
       |  note for $className "${dlg.Self}"
       |
       |class $className{
          ${recs.mkString("\n")}

          ${acts.mkString("\n")}
       |}
       |
       ${incoming.mkString("\n")}

       ${outgoing.mkString("\n")}
       |
       |""".stripMargin
  }

  def findRelationsFor(dlg: ADlg): Map[Action[?], ADlg] = ??? 
/*
  {
    val res = for (frm <- frms; act <- frm._2.actions) yield {
      act._2 -> frm._2
    }
    val nonEmpty = res.filterNot(_._1.target.name == Unknown_.name)
    for (x <- nonEmpty) {
      //      println( x._1.target + " " + x._2.Self)
    }
    //println( win.fullType)
    nonEmpty.filter(_._1.target.name == dlg.Self)
  }
*/

}

