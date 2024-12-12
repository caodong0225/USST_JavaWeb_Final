package usst.web.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usst.web.entity.User;
import usst.web.service.IUserService;

/**
 * @author jyzxc
 * @since 2024-12-10
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Resource
    IUserService userService;
    @GetMapping("/login")
    public String login() {
        // This will resolve to login.html
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    // 处理注册表单提交
    @PostMapping("/register")
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        // 模拟简单验证逻辑
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("error", "用户名和密码不能为空");
            // 返回注册页面并显示错误
            return "register";
        }
        if(userService.isUserExist(username)){
            model.addAttribute("error", "用户名已存在");
            // 返回注册页面并显示错误
            return "register";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(!userService.registerUser(user))
        {
            model.addAttribute("error", "注册失败");
            // 返回注册页面并显示错误
            return "register";
        }
        // 注册成功后重定向到登录页面
        return "redirect:/auth/login";
    }
    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("error", "用户名和密码不能为空");
            // 返回登录页面并显示错误
            return "login";
        }
        if(!userService.isUserExist(username)){
            model.addAttribute("error", "用户不存在");
            // 返回登录页面并显示错误
            return "login";
        }
        User user = userService.isCorrect(username, password);
        if(user == null){
            model.addAttribute("error", "密码错误");
            // 返回登录页面并显示错误
            return "login";
        }
        // 将用户信息写入 Session
        session.setAttribute("user", user);
        // 登录成功后重定向到用户详情页面
        return "redirect:/home/index";
    }
}
