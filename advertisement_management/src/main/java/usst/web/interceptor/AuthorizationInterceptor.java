package usst.web.interceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import usst.web.dto.UserInfoDTO;
import usst.web.entity.User;
import usst.web.service.IRoleService;

import java.util.Arrays;
import java.util.List;


/**
 * @author jyzxc
 * @since 2024-12-12
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Resource
    IRoleService roleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从 Session 中获取用户信息
        User userSession = (User) request.getSession().getAttribute("user");
        System.out.println(request.getRequestURI());
        if (request.getRequestURI().startsWith("/ad/")) {
            return true;
        }

        // 检查用户是否已登录
        if (userSession == null) {
            // 未登录则重定向到登录页面
            response.sendRedirect("/auth/login");
            return false;
        }

        // 获取用户角色
        UserInfoDTO user = new UserInfoDTO();
        user.setId(userSession.getId());
        user.setRoleName(roleService.getRoleNameByUserId(userSession.getId()));
        user.setUsername(userSession.getUsername());

        // 获取当前请求的路径
        String requestURI = request.getRequestURI();

        // 定义权限规则 (路径 -> 需要的权限)
        List<String> adminPages = Arrays.asList("/admin", "/manage");
        List<String> userPages = Arrays.asList("/profile", "/dashboard");

        // 检查权限
        if (adminPages.contains(requestURI) && !"admin".equals(user.getRoleName())) {
            // 如果是管理员页面，且用户不是管理员，禁止访问
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "无权访问该页面");
            return false;
        } else if (userPages.contains(requestURI) && !"USER".equals(user.getRoleName()) && !"ADMIN".equals(user.getRoleName())) {
            // 如果是普通用户页面，且用户角色不匹配，禁止访问
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "无权访问该页面");
            return false;
        }

        // 校验通过，继续处理请求
        return true;
    }
}
