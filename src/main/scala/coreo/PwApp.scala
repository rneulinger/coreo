package coreo

import com.microsoft.playwright.*

import scala.jdk.CollectionConverters.*
import java.io.PrintWriter

class PwApp(val baseUrl: String) extends AnyApp {
  def nameOfApp = "No Name"
  final def page: Page = pg

  override def app: PwApp = this

  def predefBaseUrls = Map[String, String]()

  def Self = this

  lazy val playwright: Playwright = Playwright.create()

  lazy val bOpts = new BrowserType.LaunchOptions().setHeadless(false)

  lazy val browser: Browser = playwright
    .chromium()
    .launch(bOpts)

  lazy val cOpts: Browser.NewContextOptions = new Browser.NewContextOptions()
    .setIgnoreHTTPSErrors(true)

  lazy val context: BrowserContext = browser.newContext(cOpts) // HTTPS-Fehler ignorieren

  lazy val pg: Page = {
    val tmp = context.newPage()
    tmp.navigate(baseUrl)
    tmp
  }

  def setTimeout(ms: Double) = {
    pg.setDefaultTimeout(ms)
  }

  def flash(loc: Locator): Locator = {
    loc.evaluate("element => {" +
      "element.style.transition = 'background-color 0.3s ease';" +
      "element.style.backgroundColor = 'yellow';" +
      "setTimeout(() => element.style.backgroundColor = '', 500);" +
      "}")
    Thread.sleep(1000)
    loc
  }

  // Cookies auslesen
  private lazy val cookie = context.cookies().asScala

  //  cookies.foreach(cookie => println(s"${cookie.name()} = ${cookie.value()}"))
  def close(): Unit = {
    browser.close()
    playwright.close()
  }

  def pause(): Unit = pg.pause()

  def home: Response = pg.navigate(baseUrl)


  /**
   * current view
   */

  def openUrl(path: String): Unit = pg.navigate(baseUrl + path)

  def gui: GUI = GUI(this)

  def writeTextToFile(s: String, f: String): Unit = {
    //  println( f)
    val writer = new PrintWriter(f)
    writer.write(s)
    writer.close()
  }


  def capitalizeFirst(s: String): String =
    if (s.isEmpty) s
    else s.head.toUpper + s.tail

  def genAllTs(name:String): Unit = {
    val capName = capitalizeFirst(name)
    val decl = for (f <- frms) yield {
      val fn = f._2.myType + "_.ts"
      val code = Formatter(f._2).mkTs
      if (f._2.atoms.nonEmpty) {
        writeTextToFile(code, "build/" + fn)
        s"public readonly _${f._2.myType}:${f._2.myType}_;"
      }
      else {
        ""
      }
    }
    println("-" * 20)
    println(decl.toList.filter(_.nonEmpty).mkString("\n"))

    val inst = for (f <- frms) yield {
      if (f._2.atoms.nonEmpty) {
        s" this._${f._2.myType} = new ${f._2.myType}_(this);"
      }
      else {
        ""
      }
    }
    val res =
      s"""namespace Generic{
         |
         |//using Core;
         |
         |// ReSharper disable InconsistentNaming
         |
         |export class $capName extends Core.ROOT
         |{
       ${decl.toList.filter(_.nonEmpty).sorted.distinct.mkString("\n")}
         |constructor( baseUrl:String ){
         | super(baseUrl);
       ${inst.toList.filter(_.nonEmpty).sorted.distinct.mkString("\n")}
         |}
         |}
         |}""".stripMargin
    writeTextToFile(res, s"build/$capName.ts")

  }

  def genAllCs(name:String): Unit = {
    val capName = capitalizeFirst(name)

    val decl = for (f <- frms) yield {
      val fn = f._2.myType + "_.cs"
      val code = Formatter(f._2).mkCs
      if (f._2.atoms.nonEmpty) {
        writeTextToFile(code, "build/" + fn)
        s"public ${f._2.myType}_ _${f._2.myType};"
      }
      else {
        ""
      }
    }
    println("-" * 20)
    println(decl.toList.filter(_.nonEmpty).mkString("\n"))

    val inst = for (f <- frms) yield {
      if (f._2.atoms.nonEmpty) {
        s" _${f._2.myType} = new ${f._2.myType}_(this);"
      }
      else {
        ""
      }
    }
    val res =
      s"""namespace Generic;
         |
         |using Core;
         |
         |// ReSharper disable InconsistentNaming
         |
         |export class $capName : ROOT
         |{
       ${decl.toList.filter(_.nonEmpty).sorted.distinct.mkString("\n")}
         |constructor( string baseUrl ){
       ${inst.toList.filter(_.nonEmpty).sorted.distinct.mkString("\n")}
         |}
         |}
         |""".stripMargin
    writeTextToFile(res, s"build/$capName.cs")
  }

  def genCsToBuild(name: String): Unit = {
    val capName = capitalizeFirst(name)
    val dest = "build/cs/"

    def dropName(s: String) =
      if s.startsWith(s"$name.") then s.substring(name.length + 1) else s

    def allPacks: List[String] = {
      val res = for (frm <- short) yield frm._2.getClass.getPackage.getName

      res.toList.distinct.sorted.filterNot(_ == "coreo").map(dropName(_))
    }

    def mkDirectory(any: Any): String = {
      val dir = dest + any.getClass.getPackage.getName.replace(".", "/")
      java.io.File(dir).mkdirs()
      dir
    }

    for (frm <- short) {
      java.io.File(dest).mkdirs()
      val code =
        s"""namespace ${s"$capName." + dropName(frm._2.getClass.getPackage.getName)};
           |using Coreo;
           |
           |${Formatter(frm._2).mkCs}
           |""".stripMargin

      println(frm._1)
      val fn = s"${mkDirectory(frm._2)}/${frm._1}_.cs"
      println(fn)

      writeTextToFile(code, fn)


      def genAllUsings: String = {
        val res = for (p <- allPacks) yield s"using $p;"
        res.mkString("\n")
      }

      def allNames = short.map(_._1).filterNot(_.isEmpty).filterNot(_ == "coreo")

      def genAllDecl: String = {
        val names = for (p <- allNames) yield {
          s"public readonly ${p}_ ${p};"
        }
        names.toList.sorted.mkString("\n")
      }

      def genAllDefs: String = {
        val names = for (p <- allNames) yield {
          s" ${p}  = new ${p}_(this);"
        }
        names.toList.sorted.mkString("\n")
      }

      val app =
        s"""
           |namespace $capName;
           |using Microsoft.Playwright;
           |using Coreo;
           |
           |$genAllUsings
           |public class App:PwApp{
           |
           | $genAllDecl
           |
           | public App( IPage page ):base(page){
           |  $genAllDefs
           | }
           |
           | public App app() { return this; }
           |
           |}
           |""".stripMargin
      writeTextToFile(app, dest + s"$capName/App.cs")
    }
  }

  def mkMermaids():Unit = {
    for (frm <- short) {
      val path = "build/mermaid/" + frm._2.getClass.getPackageName.replace(".", "/")
      println(path)
      java.io.File(path).mkdirs()
      val code = mkMermaid(frm._2)
      val mermaid =
        s"""
           |""".stripMargin
      writeTextToFile(code, s"$path/${frm._2.simple}.mermaid")
    }
    val incs = for (frm <- short.values.toList.sortBy(_.getClass.getName)) yield {
      val path = "" + frm.getClass.getName.replace(".", "/")
      val cn = frm.getClass.getName
      val name = cn
      s"""== $name
         |[mermaid]
         |----
         |include::${path}.mermaid[]
         |----
         |
         |""".stripMargin
    }
    val adoc =
      s""":toc:
         |
         |= Mermaid diagrams
         |
         |${incs.mkString("\n")}
         |""".stripMargin
    writeTextToFile(adoc, s"build/mermaid/Mermaids.adoc")
  }

}


object PwApp {

}