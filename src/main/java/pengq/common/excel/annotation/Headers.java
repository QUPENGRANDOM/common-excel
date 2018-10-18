package pengq.common.excel.annotation;

import pengq.common.excel.model.EXCell;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Headers {
    String[] names() default {};

    EXCell[] cells() default {};
}
