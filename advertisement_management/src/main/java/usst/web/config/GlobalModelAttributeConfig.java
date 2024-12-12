package usst.web.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author jyzxc
 * @since 2024-12-12
 */
@Component
@ControllerAdvice
public class GlobalModelAttributeConfig {
    @ModelAttribute
    public void addUserToModel(Model model, HttpSession session, HttpServletRequest request) {
        Object user = session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        // 获取当前访问路径
        String currentPath = request.getRequestURI();
        model.addAttribute("currentPath", currentPath);
    }
}
