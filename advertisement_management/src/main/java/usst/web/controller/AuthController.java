package usst.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author jyzxc
 * @since 2024-12-10
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/login")
    public String login(Model model) {
        return "login";  // This will resolve to login.jsp
    }
    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }
}
