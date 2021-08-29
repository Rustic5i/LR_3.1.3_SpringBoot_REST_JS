package com.baratov.spring.springboot.model.DTO;

import com.baratov.spring.springboot.dao.DAO;
import com.baratov.spring.springboot.model.Role;
import com.baratov.spring.springboot.model.User;
import com.baratov.spring.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverterDTO {
    private DAO dao;

    @Autowired
    public UserConverterDTO(DAO dao) {
        this.dao = dao;
    }

    public UserDTO convertUserToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLastName(user.getLastName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setAge(user.getAge());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        String[] roles = user.getRoles().stream().map(role -> role.getAuthority()).collect(Collectors.toList()).toArray(String[]::new);
        userDTO.setRoles(roles);
        return userDTO;
    }
    public User converUserDtoToUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        Set<Role> roles = dao.getSetRoles(userDTO.getRoles());
        user.setRoles(roles);
        return user;
    }

    public List<UserDTO> convertAllToDTO(List<User> userList) {
        return userList.stream()
                .map(this::convertUserToUserDTO).collect(Collectors.toList());
    }
}
