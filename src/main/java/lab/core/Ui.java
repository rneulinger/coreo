package lab.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * defines a name for a ui component
 * in complicated cases it is required to use a special name eg "%"
 */

@Retention(RetentionPolicy.RUNTIME)

public @interface Ui {
    String value();
}
