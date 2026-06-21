package coreo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * focus goes to this window, usable in Actions.
 * if RETURN is provided as parameter action returns to previous dialog
 * if Overload is provided as parameter overloading is needed
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD, ElementType.METHOD})   // TYPE = class, interface, enum, record

public @interface BackTo {
}
