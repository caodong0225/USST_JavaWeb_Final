package usst.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usst.web.response.BaseResponse;

/**
 * @author jyzxc
 * @since 2024-12-10
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping(value = "/login", produces = "application/json")
    public BaseResponse login() {
        return BaseResponse.makeResponse(200, "ok");
    }
}
