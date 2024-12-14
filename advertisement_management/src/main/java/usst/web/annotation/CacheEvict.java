package usst.web.annotation;

import java.lang.annotation.*;

/**
 * @author jyzxc
 * @since 2024-12-14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheEvict {
    // 缓存的 key
    String key();
    int index() default -1;
}
