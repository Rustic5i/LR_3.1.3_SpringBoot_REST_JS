package com.baratov.spring.springboot.controller;

import com.baratov.spring.springboot.model.User;
import com.baratov.spring.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    private IUserService service;
    @Autowired
    public MyRESTController(IUserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    private List<User> allUsers(){
        List<User> list = service.getAllUsers();
        return  list;
    }
}
