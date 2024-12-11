package usst.web.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import usst.web.response.BaseDataResponse;
import usst.web.service.IUserService;

/**
 * @author jyzxc
 * @since 2024-12-11
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    @GetMapping(value = "/{id}", produces = "application/json")
    public BaseDataResponse getUserById(@PathVariable("id") Integer id)
    {
        return new BaseDataResponse(userService.getUserById(id));
    }
}
