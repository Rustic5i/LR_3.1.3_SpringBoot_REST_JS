package com.baratov.spring.springboot.controller;

import com.baratov.spring.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {
    private IUserService userService;
////test_1
    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public String indexUser() {
        return "view/user";
    }
    @GetMapping("/admin")
    public String indexAdmin() {
        return "view/admin";
    }
}
