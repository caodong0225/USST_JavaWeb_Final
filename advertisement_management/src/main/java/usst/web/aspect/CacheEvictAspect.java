package usst.web.aspect;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import usst.web.annotation.CacheEvict;

/**
 * @author jyzxc
 * @since 2024-12-14
 */
@Aspect
@Component
public class CacheEvictAspect {

    // 获取 HttpServletRequest 对象
    @Resource
    private HttpServletRequest request;

    @Before("@annotation(cacheEvict)")
    public void handleCacheEvict(ProceedingJoinPoint joinPoint, CacheEvict cacheEvict) {
        // 获取注解中的 key
        String key = cacheEvict.key();
        int index = cacheEvict.index();
        Object[] args = joinPoint.getArgs();
        if(index > 0){
            key += args[index];
        }
        // 获取 ServletContext 对象
        ServletContext servletContext = request.getServletContext();

        // 从缓存中移除指定的 key
        Object cachedValue = servletContext.getAttribute(key);
        if (cachedValue != null) {
            servletContext.removeAttribute(key);
        }
    }
}

