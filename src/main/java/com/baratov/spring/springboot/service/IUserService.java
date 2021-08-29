package com.baratov.spring.springboot.service;

import com.baratov.spring.springboot.model.DTO.UserDTO;
import com.baratov.spring.springboot.model.Role;
import com.baratov.spring.springboot.model.User;
import com.baratov.spring.springboot.myExcetion.SaveObjectException;
import java.util.List;
import java.util.Set;

public interface IUserService {
    UserDTO findByUserEmail(String userEmail);

    void registrationUser(UserDTO newUser) throws SaveObjectException;

    List<UserDTO> getAllUsers();

    Set<Role> getSetRoles(String... roles);

    void removeUserById(Long id);

    UserDTO getUserById(Long id);

    void updateUser(UserDTO updateUserDTO) throws SaveObjectException;
}
