package icu.mikanku.gobangbackend.user.controller;

import icu.mikanku.gobangbackend.user.entity.dto.UserRequest;
import icu.mikanku.gobangbackend.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRequest userRequest) {
        userService.register(userRequest.getUsername(), userRequest.getPassword());
        return "success";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequest userRequest) {
        return userService.login(userRequest.getUsername(), userRequest.getPassword());
    }

}
