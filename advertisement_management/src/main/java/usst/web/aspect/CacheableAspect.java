package usst.web.aspect;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import usst.web.annotation.Cacheable;

/**
 * @author jyzxc
 * @since 2024-12-12
 */
@Aspect
@Component
public class CacheableAspect {
    // 获取HttpServletRequest对象
    @Resource
    private HttpServletRequest request;

    @Around("@annotation(cacheable)")
    public Object handleCacheable(ProceedingJoinPoint joinPoint, Cacheable cacheable) throws Throwable {
        String key = cacheable.key();
        Object[] args = joinPoint.getArgs();
        // 获取ServletContext对象
        ServletContext servletContext = request.getServletContext();
        // 将方法参数放入上下文
        int index = cacheable.index();
        if(index >= 0){
            key += args[index];
        }

        // 检查缓存是否存在
        Object cachedValue = servletContext.getAttribute(key);
        if (cachedValue != null) {
            return cachedValue;
        }

        // 执行目标方法
        Object result = joinPoint.proceed();
        if(result != null) {
            // 如果结果不为空，将结果存入缓存
            servletContext.setAttribute(key, result);
            // 注意：ServletContext没有过期控制，你可能需要手动处理过期逻辑
        }
        return result;
    }
}