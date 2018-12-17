package pengq.common.excel.annotation;

import pengq.common.excel.model.EXCell;
import pengq.common.excel.model.Null;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WriteCell {
    EXCell writeCell();

    Class target() default Null.class;

    String header() default "";

    String dateFormat() default "";

    String doubleFormat() default "";
}
