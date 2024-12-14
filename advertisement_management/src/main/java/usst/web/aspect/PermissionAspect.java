package usst.web.aspect;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import usst.web.annotation.Permission;
import usst.web.entity.User;
import usst.web.service.IRoleService;

import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;

/**
 * @author jyzxc
 * @since 2024-12-14
 */
@Aspect
@Component
public class PermissionAspect {
    @Resource
    private HttpServletRequest request;
    @Resource
    IRoleService roleService;
    // 前置通知，拦截带有 @Permission 注解的方法
    @Before("@annotation(usst.web.annotation.Permission)")
    public void checkPermission(JoinPoint joinPoint) throws AccessDeniedException {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 获取 @Permission 注解
        Permission permission = method.getAnnotation(Permission.class);
        if (permission != null) {
            String requiredRole = permission.role();

            // 模拟获取当前用户角色（具体实现视业务而定）
            String currentUserRole = getCurrentUserRole();

            // 校验权限
            if (!requiredRole.equals(currentUserRole)) {
                throw new AccessDeniedException("权限不足");
            }
        }
    }

    /**
     * 获取当前用户角色的逻辑（需要结合具体的业务实现）
     * 这里可以是从 JWT Token 或 Session 中获取
     */
    private String getCurrentUserRole() {
        User userSession = (User) request.getSession().getAttribute("user");
        if(userSession != null)
        {
            return roleService.getRoleNameByUserId(userSession.getId());
        }
        // 示例：从线程上下文中获取当前用户信息
        return null; // 假设当前角色是 "user"
    }
}
