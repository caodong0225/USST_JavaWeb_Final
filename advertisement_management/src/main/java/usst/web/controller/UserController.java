package usst.web.controller;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import usst.web.dto.UserGeneralDTO;
import usst.web.response.BaseDataResponse;
import usst.web.service.IUserService;

import java.util.List;

/**
 * @author jyzxc
 * @since 2024-12-11
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    @GetMapping(value = "/{id}", produces = "application/json")
    public BaseDataResponse getUserById(@PathVariable("id") Integer id)
    {
        return new BaseDataResponse(userService.getUserById(id));
    }
    @GetMapping(value = "/list")
    public String getUsers(
            Model model
    )
    {
        List<UserGeneralDTO> users = userService.getUsers();
        model.addAttribute("userList", users);
        return "users";
    }
}
