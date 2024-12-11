package usst.web.controller;

import jakarta.annotation.Resource;
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
        return "login";  // This will resolve to login.html
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
            return "register";  // 返回注册页面并显示错误
        }
        if(userService.isUserExist(username)){
            model.addAttribute("error", "用户名已存在");
            return "register";  // 返回注册页面并显示错误
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(!userService.registerUser(user))
        {
            model.addAttribute("error", "注册失败");
            return "register";  // 返回注册页面并显示错误
        }
        // 注册成功后重定向到登录页面
        return "redirect:/auth/login";
    }
    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("error", "用户名和密码不能为空");
            return "login";  // 返回登录页面并显示错误
        }
        if(!userService.isUserExist(username)){
            model.addAttribute("error", "用户不存在");
            return "login";  // 返回登录页面并显示错误
        }
        if(!userService.isCorrect(username, password)){
            model.addAttribute("error", "密码错误");
            return "login";  // 返回登录页面并显示错误
        }
        // 登录成功后重定向到用户详情页面
        return "index";
    }
}
