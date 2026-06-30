package lab.core

import java.util.regex.Pattern
class Utils {

}

import java.lang.reflect.{Field, Modifier}

object ReflectUtils {

  /** Returns all declared fields from `clazz` and every superclass (excluding java.lang.Object).
   * By default, includes interface fields and makes each field accessible.
   *
   * @param clazz             The class to inspect
   * @param includeInterfaces Whether to include fields declared on interfaces (usually static finals)
   * @param makeAccessible    Whether to call setAccessible(true) on each field
   * @return Seq[Field]
   */
  def allDeclaredFields(
                         clazz: Class[?],
                         includeInterfaces: Boolean = true,
                         makeAccessible: Boolean = true
                       ): Seq[Field] = {

    val seen = scala.collection.mutable.Set[Field]()
    val acc = scala.collection.mutable.ArrayBuffer[Field]()

    // Walk superclasses
    var c: Class[?] = clazz
    while (c != null && c != classOf[Object]) {
      for (f <- c.getDeclaredFields) {
        if (makeAccessible) f.setAccessible(true)
        if (!seen.contains(f)) {
          acc += f
          seen += f
        }
      }
      c = c.getSuperclass
    }

    // Optionally include interface fields (typically public static final)
    if (includeInterfaces) {
      def visitInterfaces(cls: Class[?]): Unit = {
        for (intf <- cls.getInterfaces) {
          for (f <- intf.getDeclaredFields) {
            if (makeAccessible) f.setAccessible(true)
            if (!seen.contains(f)) {
              acc += f
              seen += f
            }
          }
          // Recurse into parent interfaces
          visitInterfaces(intf)
        }
      }

      visitInterfaces(clazz)
    }

    acc.toSeq
  }

  /** Convenience: only instance (non-static) fields */
  def allInstanceFields(clazz: Class[?]): Seq[Field] =
    allDeclaredFields(clazz).filterNot(f => Modifier.isStatic(f.getModifiers))


  private val boundaries =
    """(?<=[\p{Ll}\p{Nd}])(?=\p{Lu})|(?<=\p{Lu})(?=\p{Lu}\p{Ll})""".r

  def splitIdentifier(s: String): List[String] =
    s.split("[_-]+")
      .iterator
      .flatMap(part => boundaries.split(part))
      .filter(_.nonEmpty)
      .toList

  /**
   * @param s
   * @param additionalChars
   * @return
   */
  def normalizeSeparators(s: String, additionalChars: String): String =
    val chars = Pattern.quote(additionalChars)
    s.replaceAll(s"[\\s$chars]+", " ").trim


  def normalizeSeparatorsSave(s: String, additionalChars: String): String =
    val pattern =
      s"[\\s${Pattern.quote(additionalChars).stripPrefix("\\Q").stripSuffix("\\E")}]+"

    s.replaceAll(pattern, " ").trim

  @main
  def splitIdentifierTest()={

    val examples = Seq(
      "camelCase",
      "PascalCase",
      "snake_case",
      "kebab-case",
      "XMLParser",
      "HTTPRequestHandler",
      "ÄpfelSindLecker",
      "StraßenName",
      "ПриветМир",
      "ΚαλημέραΚόσμε",
      "HTTP jjj",
      "  HTTP jjj  ",
    )

    examples.foreach { s =>
      println(s"$s -> ${splitIdentifier(s)}")
    }

  }
}

