package usst.web.config;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import usst.web.dto.UserInfoDTO;
import usst.web.entity.User;
import usst.web.service.IRoleService;

/**
 * @author jyzxc
 * @since 2024-12-12
 */
@Component
@ControllerAdvice
public class GlobalModelAttributeConfig {
    @Resource
    IRoleService roleService;
    @ModelAttribute
    public void addUserToModel(Model model, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            UserInfoDTO userInfoDTO = new UserInfoDTO();
            String roleName = roleService.getRoleNameByUserId(user.getId());
            userInfoDTO.setRoleName(roleName);
            userInfoDTO.setId(user.getId());
            userInfoDTO.setUsername(user.getUsername());
            model.addAttribute("user", userInfoDTO);
        }
        // 获取当前访问路径
        String currentPath = request.getRequestURI();
        model.addAttribute("currentPath", currentPath);
    }
}
