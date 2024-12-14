package usst.web.annotation;

import java.lang.annotation.*;

/**
 * @author jyzxc
 * @since 2024-12-14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
    // 需要的权限
    String role();
}
