package com.baratov.spring.springboot.controller;


import com.baratov.spring.springboot.model.DTO.UserDTO;
import com.baratov.spring.springboot.model.User;
import com.baratov.spring.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class RESTControllerUser {
    ////test_1.1
    private IUserService service;

    @Autowired
    public RESTControllerUser(IUserService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public UserDTO getUser(Principal principal){
        return service.findByUserEmail(principal.getName());
    }
}
