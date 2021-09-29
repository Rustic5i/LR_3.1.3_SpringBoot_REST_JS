package com.baratov.spring.springboot.controller;

import com.baratov.spring.springboot.model.DTO.UserDTO;
import com.baratov.spring.springboot.myExcetion.NoSuchUserException;
import com.baratov.spring.springboot.myExcetion.SaveObjectException;
import com.baratov.spring.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTControllerAdmin {
//master.2
    private IUserService service;
    @Autowired
    public RESTControllerAdmin(IUserService service) {
        this.service = service;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<UserDTO>> allUsers(){
        List<UserDTO> users = service.getAllUsers();
        return  new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public UserDTO getUser(@PathVariable Long id){
        UserDTO userDTO = service.getUserById(id);
        if(userDTO==null){
            throw new NoSuchUserException("There is no user with ID "+id+" in Database");
        }
        return userDTO;
    }
    @PostMapping(value = "/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewUser(@RequestBody UserDTO userDTO) throws SaveObjectException {
        service.registrationUser(userDTO);
    }

    @PatchMapping(value = "/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody UserDTO userDTO) throws SaveObjectException {
        service.updateUser(userDTO);
    }
    @DeleteMapping("/admin/{id}")
    public void deleteUser(@PathVariable Long id){
        service.removeUserById(id);
    }
}
