package coreo

class Formatter(val frm: WIN) {
  private def atoms = frm.atoms

  private def datas = frm.datas

  private def actions = frm.actions

  private def fullType = frm.fullType

  private def myType = frm.myType

  private def path = frm.path

  private val BLANK = " ".charAt(0)

  private def atomsMax = if atoms.isEmpty then 0 else atoms.keySet.map(_.length).max

  private def shortMax = if atoms.isEmpty then 0 else atoms.values.map(_.fullName.length).max

  private def typeMax = if atoms.isEmpty then 0 else atoms.values.map(_.myType.length).max

  /**
   * Add block for Gherkin
   *
   * @return
   */
  def mkAdd: String = {
    val head = s"""|| ${"name".padTo(atomsMax, " ".charAt(0))} |   | typ |"""
    val lines = for (a <- atoms.filterNot(_._2.isInstanceOf[BTN[?, ?]])) yield {
      s"""|| ${a._1.padTo(atomsMax, BLANK)} |   | ${a._2.myType.padTo(typeMax, BLANK)} |"""
    }
    val tbl = List(head) ::: lines.toList
    s"""
       |* add: '$fullType'
      ${tbl.mkString("\n")}
       |""".stripMargin
  }

  /**
   * Edit block for Gherkin
   *
   * @return
   */
  def mkEdit: String = {
    val head = s"""|| ${"name".padTo(atomsMax, " ".charAt(0))} |   | typ |"""
    val lines = for (a <- atoms.filterNot(_._2.isInstanceOf[BTN[?, ?]])) yield {
      s"""|| ${a._1.padTo(atomsMax, BLANK)} |   | ${a._2.myType.padTo(typeMax, BLANK)} |"""
    }
    val tbl = List(head) ::: lines.toList
    s"""
       |* edit: '$fullType'
      ${tbl.mkString("\n")}
       |""".stripMargin
  }

  /**
   * Next block for Gherkin
   *
   * @return
   */
  def mkNext: String = {
    val head = s"""|| ${"name".padTo(atomsMax, " ".charAt(0))} |   | typ |"""
    val lines = for (a <- atoms.filterNot(_._2.isInstanceOf[BTN[?, ?]])) yield {
      s"""|| ${a._1.padTo(atomsMax, BLANK)} |   | ${a._2.myType.padTo(typeMax, BLANK)} |"""
    }
    val tbl = List(head) ::: lines.toList
    s"""
       |* next: '$fullType'
      ${tbl.mkString("\n")}
       |""".stripMargin
  }

  /**
   * Set block for Gherkin
   *
   * @return
   */
  def mkSet: String = {
    val head = s"""|| ${"name".padTo(atomsMax, " ".charAt(0))} |   | typ |"""
    val lines = for (a <- atoms.filterNot(_._2.isInstanceOf[BTN[?, ?]])) yield {
      s"""|| ${a._1.padTo(atomsMax, BLANK)} |   | ${a._2.myType.padTo(typeMax, BLANK)} |"""
    }
    val tbl = List(head) ::: lines.toList
    s"""
       |* set: '$fullType'
      ${tbl.mkString("\n")}
       |""".stripMargin
  }

  /**
   * Action block for Gherkin
   *
   * @return
   */
  def mkAct: String = {
    val head = s"""|| ${"name".padTo(atomsMax, " ".charAt(0))} | op | p1 | p2 | p3 | typ |"""
    val lines = for (a <- atoms) yield {
      s"""|| ${a._1.padTo(atomsMax, BLANK)} |    |    |    |    | ${a._2.myType.padTo(typeMax, BLANK)} |"""
    }
    val tbl = List(head) ::: lines.toList
    s"""
       |* act: '$fullType'
      ${tbl.mkString("\n")}
       |""".stripMargin
  }

  /**
   * Get block for Gherkin
   *
   * @return
   */
  def mkGet: String = {
    val head = s"""|| ${"name".padTo(atomsMax, BLANK)} | op | ${"var".padTo(shortMax, BLANK)} |"""
    val lines = for (a <- atoms.filterNot(_._2.isInstanceOf[BTN[?, ?]])) yield {
      s"""|| ${a._1.padTo(atomsMax, BLANK)} |    | ${a._2.shortName.padTo(shortMax, BLANK)} |"""
    }
    val tbl = List(head) ::: lines.toList
    s"""
       |* get: '$fullType'
       ${tbl.mkString("\n")}
       |""".stripMargin
  }

  /**
   * Chk block for Gherkin
   *
   * @return
   */
  def mkChk: String = {
    val head = s"""|| ${"name".padTo(atomsMax, " ".charAt(0))} | op | ref |"""
    val lines = for (a <- atoms) yield {
      s"""|| ${a._1.padTo(atomsMax, BLANK)} | == |     |"""
    }
    val tbl = List(head) ::: lines.toList
    s"""
       |* chk: '$fullType'
     ${tbl.mkString("\n")}
       |""".stripMargin

  }

  /**
   * C# erzeugen
   */
  def mkCs: String = {
    val path = if frm.path.trim.isEmpty then "" else s"\npublic override String path(){ return \"${frm.path.trim}\"; }\n"

    val lines = for (a <- atoms) yield {
      val name = a._2.nameUi
      //      val short = Defs.mkCamelCase(name)
      val short = Defs.mkCamelCase(a._2.fullName)
      val n = if name == short then "" else name
      val typ = a._2.myType
      s"""|        $short = $typ(\"$n\");"""
    }

    val decls = for (a <- atoms) yield {
      val name = a._2.nameUi
      //val short = Defs.mkCamelCase(name)
      val short = Defs.mkCamelCase(a._2.fullName)
      val typ = a._2.myType
      s"""|    public readonly $typ $short;"""
    }

    val recs = for (a <- atoms.filter(_._2.isInstanceOf[DATA[?]])) yield {
      val name = a._2.nameUi
      //      val short = Defs.mkCamelCase(name)
      val short = Defs.mkCamelCase(a._2.fullName)
      "    string? " + short + " = null"
    }

    val targets = {
      def dropTrailingUnderscore(s: String): String = {
        if s.endsWith("_") then s.dropRight(1) else s
      }

      val res = for (a <- atoms.filter(_._2.isInstanceOf[ACTION[?, ?]])) yield {
        val short = Defs.mkCamelCase(a._2.fullName)
        a._2 match {
          case x: ACTION[?, ?] if x.target == Unknown_
          => ""
          case x: ACTION[?, ?]
          => s"""$short.target = "${dropTrailingUnderscore(x.target.simple)}";"""
        }
      }
      res.filterNot(_.isEmpty).toList.sorted.mkString("\n")
    }

    s"""
       |// ReSharper disable InconsistentNaming
       |public class ${myType}_ : FRM{
       |  // tag::fields[]
       |  public ${myType}_( PwApp app ):base(app) {
          ${lines.mkString("\n")}
       |
       |  $targets
       |  }
       |  $path
       |  // end::fields[]
       |  public record Rec${recs.mkString("(\n", ",\n|", ")\n:GenRec")}
       |  {
       |      public Rec fromTable(DataTable table) { return table.CreateInstance<Rec>(); }
       |  }
       |  public override Rec rec() { return new Rec(); }
       |  public override App app() { return (App)base.app(); }
       ${decls.mkString("\n")}
       |}
       |""".stripMargin
  }

  /**
   * typescript sharp erzeugen
   */
  def mkTs: String = {
    val Core = "Coreo"
    val lines = for (a <- atoms) yield {
      val name = a._2.nameUi
      val short = Defs.mkCamelCase(name)
      val typ = a._2.myType
      s"""|        this.$short = new $Core.$typ(this, \"$name\");"""
    }
    val decls = for (a <- atoms) yield {
      val name = a._2.nameUi
      val short = Defs.mkCamelCase(name)
      val typ = a._2.myType
      s"""|    public readonly $short : $Core.$typ;"""
    }

    s"""namespace Generic{
       |
       |//import {BTN,TXT,FRM,CanOwn} from '../Core';
       |
       |export class ${myType}_ extends $Core.FRM{
       ${decls.mkString("\n")}
       |
       |  constructor( own:$Core.CanOwn ){
       |    super(own)
          ${lines.mkString("\n")}
       |  }
       |}
       |// ${myType}_ _$myType = new $myType( this );
       |}
       |""".stripMargin
  }

  /**
   * create AsciiDoc snipped (copy to clipboard)
   *
   * @return
   */
  def mkDoc: String = {
    def mkLink = if path.isEmpty then "" else s"* http{ip}/$path[]"

    def mkInc = if path.isEmpty then "" else s"$path/"

    def mkArrow = if path.isEmpty then "" else " ->"
    s"""
       |=== $myType$mkArrow
       |
       |$path
       |
       |image::$myType.png[]
       |
       |[,java]
       |----
       |include::{SRI}/$mkInc${myType}_.scala[tag=fields]
       |----
       |
       |
       |""".stripMargin
  }
}
