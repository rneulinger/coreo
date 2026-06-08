package coreo;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * the dialog can be reached directly by the given path
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)   // TYPE = class, interface, enum, record

public @interface Go {
    String value();
}
