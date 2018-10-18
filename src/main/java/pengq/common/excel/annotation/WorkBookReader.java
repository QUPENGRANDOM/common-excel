package pengq.common.excel.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WorkBookReader {
    int startRow() default 0;
}
