package lab.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * focus goes to this window, usable in Actions.
 * if RETURN is provided as parameter action returns to previous dialog
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)   // TYPE = class, interface, enum, record

public @interface To {
    Class<? extends IsTarget> dest();
}
