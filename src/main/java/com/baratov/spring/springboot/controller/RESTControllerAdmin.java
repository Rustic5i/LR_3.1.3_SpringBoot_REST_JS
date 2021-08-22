package com.baratov.spring.springboot.controller;

import com.baratov.spring.springboot.model.User;
import com.baratov.spring.springboot.myExcetion.NoSuchUserException;
import com.baratov.spring.springboot.myExcetion.SaveObjectException;
import com.baratov.spring.springboot.myExcetion.UserInCorrectData;
import com.baratov.spring.springboot.service.IUserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTControllerAdmin {

    private IUserService service;
    @Autowired
    public RESTControllerAdmin(IUserService service) {
        this.service = service;
    }

    @GetMapping("/admin")
    public List<User> allUsers(){
        return  service.getAllUsers();
    }

    @GetMapping("/admin/{id}")
    public User getUser(@PathVariable Long id){
        User user = service.getUserById(id);
        if(user==null){
            throw new NoSuchUserException("There is no user with ID "+id+" in Database");
        }
        return user;
    }
    @PostMapping("/admin")
    public void addNewUser(@RequestBody User user) throws SaveObjectException {
        service.registrationUser(user);
    }

    @PatchMapping("/admin")
    public void updateUser(@RequestBody User user) throws SaveObjectException {
        service.updateUser(user);
    }
    @DeleteMapping("/admin/{id}")
    public void deleteUser(@PathVariable Long id){
        service.removeUserById(id);
    }
}
