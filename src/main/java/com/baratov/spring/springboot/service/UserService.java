package com.baratov.spring.springboot.service;

import com.baratov.spring.springboot.dao.DAO;
import com.baratov.spring.springboot.model.DTO.UserConverterDTO;
import com.baratov.spring.springboot.model.DTO.UserDTO;
import com.baratov.spring.springboot.model.Role;
import com.baratov.spring.springboot.model.User;
import com.baratov.spring.springboot.myExcetion.SaveObjectException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService {

    private DAO dao;
    private PasswordEncoder encoder;
    private UserConverterDTO userConverterDTO;

    @Autowired
    public UserService(DAO dao, PasswordEncoder encoder, UserConverterDTO userConverterDTO) {
        this.dao = dao;
        this.encoder = encoder;
        this.userConverterDTO = userConverterDTO;
    }

    @Override
    @Transactional
    public UserDTO findByUserEmail(String userEmail) {
        UserDTO user = userConverterDTO.convertUserToUserDTO(dao.findByUserEmail(userEmail)) ;
//        Hibernate.initialize(user.getAuthorities());
        return user;
    }

    //мы говорим Spring, эй, spring,
    // если ты видишь какое-либо исключение, Runtime exception или Checked exception,
    // пожалуйста, откатите транзакцию (не сохраняйте запись в БД) Аминь !
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void registrationUser(UserDTO newUserDto) throws SaveObjectException {
        User newUser = userConverterDTO.converUserDtoToUser(newUserDto);
        newUser.setPassword(encoder.encode(newUserDto.getPassword()));
        dao.saveUser(newUser);
    }

    @Override
    @Transactional
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userConverterDTO.convertAllToDTO(dao.getAllUsers());
        if (users.size() == 0) {
            return null;
        }
//        Hibernate.initialize(users.get(0).getAuthorities());
        return users;
    }

    @Override
    @Transactional
    public Set<Role> getSetRoles(String... roles) {
        return dao.getSetRoles(roles);
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        dao.removeUserById(id);
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserDTO userDTO = userConverterDTO.convertUserToUserDTO(dao.getUserById(id));
        return userDTO;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateUser(UserDTO updateUserDTO) throws SaveObjectException {
        User updateUser = userConverterDTO.converUserDtoToUser(updateUserDTO);
        updateUser.setPassword(encoder.encode(updateUser.getPassword()));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = dao.findByUserEmail(authentication.getName());
        if ((currentUser.getId() == updateUser.getId()) && !(currentUser.getEmail().equals(updateUser.getUsername()))) {
            dao.updateUser(updateUser);
            SecurityContextHolder.clearContext(); //logout current user
        }
        dao.updateUser(updateUser);
    }
}
