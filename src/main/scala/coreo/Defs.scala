package coreo

import com.microsoft.playwright.*

import java.util.regex.Pattern
import scala.jdk.CollectionConverters.*

//def gotoPath( path: String = "/"): Unit = {
//  import java.awt.Desktop
//  import java.net.URI
//
//  object BrowserLauncher {
//    def main(url:String): Unit = {
//      println("main:"+url)
//      if (Desktop.isDesktopSupported && Desktop.getDesktop.isSupported(Desktop.Action.BROWSE)) {
//        Desktop.getDesktop.browse(new URI(url))
//      } else {
//        println("Desktop browsing is not supported on this system.")
//      }
//    }
//  }
//  println(BaseUrl)
//  BrowserLauncher.main(BaseUrl + path)
//}

/**
 * Boolean => false byText contains, true byText exact
 * String  => id getByTestId
 * Pattern => getByText pattern
 * Double idx >= 1 byText contains[idx]
 * Double idx <= -1 byText exact[-idx]
 * Double 0 <= idx < 1  fraction as int 0.1 = 1  0.21 = 21 etc
 * Double -1 < idx < 0 tdb
 *
 */

type By =  (Page => Locator)
//type By =  (ATOM[?]) => (Page => Locator)

case class Opt(name: String = "", exact: Boolean = false) {

}

def opt(name: String | Pattern = "", exact: Boolean = false): Opt = {
  val opt = Page.GetByRoleOptions()
  name match {
    case x: String => opt.setName(x)
    case x: Pattern => opt.setName(x)
  }
  opt.setExact(exact)
  ???
}

object Defs {
  /**
   * splits up a string and make it camel case
   * "First name" -> "FirstName"
   *
   * @param s to convert
   * @return
   */

  def mkCamelCase(s: String): String = {
    val clean = s.trim
      .replace("-", " ")
      .replace(":", " ") // a few more to come
    val chunks = clean.split("\\s+").toList
    val res = for (s <- chunks) yield
      val first = s.take(1).toUpperCase()
      val rest = s.drop(1)
      first + rest

    res.mkString("")
  }

  def toClipboard(text: String): String = {
    import java.awt.Toolkit
    import java.awt.datatransfer.StringSelection
    val selection = new StringSelection(text)
    val clipboard = Toolkit.getDefaultToolkit.getSystemClipboard
    clipboard.setContents(selection, null)
    text
  }

  def gen(inp: String, frm: String = "", path: String = "", pack: String = "") = {
    val Buttons = Set("Add", "Edit", "Delete", "Next", "Finish", "Cancel", "Back")

    val tr = frm.trim
    val name = if tr.isEmpty then "New frame" else tr
    val cc = mkCamelCase(name)
    val p2 = if cc == name then "" else s", \"$name\""

    val myFrm = if cc.endsWith("_") then cc else cc + "_"

    import java.io.{PrintWriter, StringWriter}

    val baseClass = if path.isEmpty then "DLG" else "FRM"
    val sw = new StringWriter()
    val pw = new PrintWriter(sw)

    val fields = inp.lines.iterator.asScala.
      mkString(",").split(",").toList.map(_.trim).filter(_.nonEmpty)

    if fields.size != fields.toSet.size then
      throw IllegalArgumentException("Duplicate(s) in field(s) definitions")

    def declFields(): Unit = {

      def getType(s: String) = if Buttons.contains(s) then "BTN" else "TXT"

      // TODO there can be still a duplicate conflict in aliases
      for (fl <- fields.filter(_.nonEmpty)) {
        val f = (fl, mkCamelCase(fl))
        val typ = getType(f._1)
        val nm = if f._1 == f._2 then "" else f._1 // TODO improve stability \"
        pw.println(s"  val ${f._2} = $typ(\"$nm\")")
        pw.println()
      }
    }

    def mkRec(): String = {
      val tmp = for (fl <- fields.filter(_.nonEmpty).filterNot(Buttons.contains)) yield {
        "" + mkCamelCase(fl) + ": Any"
      }
      s"""
           ${tmp.mkString("|  case class Rec(", "\n|  ,", "\n| ){}")}
         |""".stripMargin

    }

    def mkImpl():String = {
      s"""  trait Impl {
         |    self: PwApp =>
         |    val ${mkCamelCase(cc)} = ${mkCamelCase(cc)}_(this)
         |  }
         |""".stripMargin
    }
    def classWithPath = {
      if baseClass == "DLG" then ""
      else
        s"""// $path
           | override def path: String = $myFrm.path
           |""".stripMargin
    }

    def objectWithPath = {
      if baseClass == "DLG" then ""
      else
        s"""override def path: String = "$path"
           |""".stripMargin
    }

    def includePack = {
      if pack.isEmpty then ""
      else s"import $pack.*"
    }

    def overrideApp = {
      if pack.isEmpty then ""
      else s"override def app:App = super.app.asInstanceOf[$pack.App]"

    }

    pw.println(
      s"""// ${"-" * 20}  $myFrm
         |import com.microsoft.playwright.*
         |import com.microsoft.playwright.options.*
         |import coreo.*
         |import coreo.bricks.*
         |$includePack
         |
         |// tag::fields[]
         |final class $myFrm ( own:CanOwn )
         |  extends $baseClass(own$p2){
         |  $classWithPath
         |""".stripMargin)

    declFields()

    pw.println(
      s"""
         |  // end::fields[]
         |  given ref: OWNER[$myFrm] = OWNER(this)
         |  $overrideApp
         |}
         |
         |object $myFrm extends Static
         |{
         |$objectWithPath
         |${mkRec()}
         |
         |${mkImpl()}
         |}
         |""".stripMargin)
    pw.flush()
    sw.toString
  }

  /**
   *
   * @param s1 first string
   * @param s2 second string
   * @return
   * println(levenshtein("kitten", "sitting")) // Output: 3
   * println(levenshtein("scala", "scala")) // Output: 0
   * println(levenshtein("flaw", "lawn")) // Output: 2
   *
   *
   */
  def levenshtein(s1: String, s2: String): Int = {
    val len1 = s1.length
    val len2 = s2.length
    val dim = len1.max(len2) + 1
    val dp: Array[Array[Int]] = Array.ofDim(dim, dim)

    for (i <- 0 to len1) dp(i)(0) = i
    for (j <- 0 to len2) dp(0)(j) = j

    for (i <- 1 to len1) {
      for (j <- 1 to len2) {
        val cost = if (s1(i - 1) == s2(j - 1)) 0 else 1
        dp(i)(j) = List(
          dp(i - 1)(j) + 1, // deletion
          dp(i)(j - 1) + 1, // insertion
          dp(i - 1)(j - 1) + cost // substitution
        ).min
      }
    }
    dp(len1)(len2)
  }

}

def gen(s: String): Unit = Defs.toClipboard(Defs.gen(s))


import scala.util.matching.Regex

object CamelCaseRegexBuilder {

  def toWhitespaceTolerantRegex(input: String): Regex = {
    require(input != null && input.nonEmpty, "Input cannot be null or empty")

    // Split camelCase / PascalCase into words
    val wordPattern =
      "[A-Z]?[a-z]+|[A-Z]+(?=[A-Z]|$)".r

    val words = wordPattern
      .findAllMatchIn(input)
      .map(m => Regex.quote(m.matched))
      .toList

    // Join with optional whitespace
    val pattern = "^" + words.mkString("\\s*") + "$"

    // Case-insensitive regex
    new Regex("(?i)" + pattern)
  }

  def test(): Unit = {

    val regex = CamelCaseRegexBuilder.toWhitespaceTolerantRegex("MyTestValue")

    println(regex.matches("my test value")) // true
    println(regex.matches("MyTestValue")) // true
    println(regex.matches("MY  TEST   VALUE")) // true
    println(regex.matches("My   TestValue")) // true
    println(regex.matches("MyTest Other")) // false
  }
}

