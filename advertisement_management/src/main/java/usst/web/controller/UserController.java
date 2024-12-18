package usst.web.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import usst.web.annotation.Permission;
import usst.web.dto.*;
import usst.web.entity.User;
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

    List<String> availableRole = List.of(new String[]{"admin", "advertisers", "siteOwner"});

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

    @PutMapping(value = "/role/{id}", produces = "application/json")
    @ResponseBody
    @Permission(role = "admin")
    public BaseResponse updateUserRoleById(@PathVariable("id") Integer id,
                                           @RequestBody UpdateUserRoleDTO updateUserRoleDTO) {
        UserInfoDTO user = userService.getUserById(id);
        if (user == null) {
            return new BaseResponse(400, "用户不存在");
        }
        String role = updateUserRoleDTO.getRoleName();
        if (!availableRole.contains(role)) {
            return new BaseResponse(400, "用户角色不合法");
        }
        if ("admin".equals(user.getRoleName())) {
            return new BaseResponse(400, "无法修改管理员角色");
        }
        if ("siteOwner".equals(role)) {
            if(!userService.deleteUserRoleById(id)){
                return new BaseResponse(400, "修改失败");
            }
        } else {
            if (!userService.updateUserRoleById(id, role)) {
                return new BaseResponse(400, "修改失败");
            }
        }
        return new BaseResponse(200, "修改成功");
    }

    @PostMapping(value = "/password/{id}", produces = "application/json")
    @Permission(role = "admin")
    @ResponseBody
    public BaseResponse updatePasswordById(@PathVariable("id") Integer id,
                                           @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        UserInfoDTO user = userService.getUserById(id);
        if (user == null) {
            return new BaseResponse(400, "用户不存在");
        }
        if("admin".equals(user.getRoleName())){
            return new BaseResponse(400, "无法修改管理员用户的密码");
        }
        if (!userService.updatePasswordById(id, updatePasswordDTO.getPassword())) {
            return new BaseResponse(400, "修改失败");
        }
        return new BaseResponse(200, "修改成功");
    }

    @PostMapping("/password/me")
    @ResponseBody
    public BaseResponse modifySelfPassword(@RequestBody UpdatePasswordDTO updatePasswordDTO,
                                           HttpSession session){
        User user = (User) session.getAttribute("user");
        if (!userService.updatePasswordById(user.getId(), updatePasswordDTO.getPassword())) {
            return new BaseResponse(400, "修改失败");
        }
        return new BaseResponse(200, "修改成功");
    }

    @PostMapping("/add")
    @Permission(role = "admin")
    @ResponseBody
    public BaseResponse addUser(@RequestBody CreateUserDTO createUserDTO){
        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setPassword(createUserDTO.getPassword());
        if(userService.isUserExist(user.getUsername())){
            return new BaseResponse(400, "用户名已存在");
        }
        if(!userService.registerUser(user)){
            return new BaseResponse(400, "添加失败");
        }
        return new BaseResponse(200, "添加成功");
    }
}
