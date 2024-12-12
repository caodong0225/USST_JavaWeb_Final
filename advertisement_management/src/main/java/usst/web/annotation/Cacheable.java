package usst.web.annotation;

import java.lang.annotation.*;

/**
 * @author jyzxc
 * @since 2024-12-12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cacheable {
    // 缓存的 key
    String key();
}
