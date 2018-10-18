package pengq.common.excel.annotation;

import pengq.common.excel.model.EXCell;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cell {
    EXCell cell();

    Class target() default Null.class;

    public static class Null {

    }
}
