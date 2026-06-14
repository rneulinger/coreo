package lab.core

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
}

