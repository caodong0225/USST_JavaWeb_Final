package usst.web.controller;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import usst.web.annotation.Permission;
import usst.web.dto.UserGeneralDTO;
import usst.web.dto.UserInfoDTO;
import usst.web.response.BaseDataResponse;
import usst.web.response.BaseResponse;
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
    @ResponseBody
    public BaseDataResponse getUserById(@PathVariable("id") Integer id) {
        return new BaseDataResponse(userService.getUserById(id));
    }

    @GetMapping(value = "/list")
    public String getUsers(
            Model model
    ) {
        List<UserGeneralDTO> users = userService.getUsers();
        model.addAttribute("userList", users);
        return "users";
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    @Permission(role = "admin")
    public BaseResponse deleteUserById(@PathVariable("id") Integer id) {
        UserInfoDTO user = userService.getUserById(id);
        if (user == null) {
            return new BaseResponse(400, "用户不存在");
        }
        if ("admin".equals(user.getRoleName())) {
            return new BaseResponse(400, "无法删除管理员");
        }
        if (!userService.deleteUserById(id)) {
            return new BaseResponse(400, "删除失败");
        }
        return new BaseResponse(200, "删除成功");
    }
}
