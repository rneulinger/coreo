package coreo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * defines a name for a ui component
 * in complicated cases it is required to use a special name eg "%"
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})   // TYPE = class, interface, enum, record

public @interface Ui {
    String value();
}
