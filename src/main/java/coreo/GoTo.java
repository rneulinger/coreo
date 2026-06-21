package coreo;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * this dialog can be reached directly by the given path.
 * the string use / as path separator and is treated always as absolute.
 * even if the leading / is omitted
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)   // TYPE = class, interface, enum, record

public @interface GoTo {
    String value();
}
