package coreo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;



/**
 * focus goes to this window, usable in Actions.
 * if RETURN is provided as parameter action returns to previous dialog
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)   // TYPE = class, interface, enum, record

public @interface To {
    Class<? extends Destination> dest() default Overload.class;
}
