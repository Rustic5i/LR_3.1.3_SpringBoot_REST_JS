package com.baratov.spring.springboot.service;

import com.baratov.spring.springboot.dao.DAO;
import com.baratov.spring.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetails implements UserDetailsService {

    private DAO dao;

    @Autowired
    public UserDetails(DAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findByUserEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found ", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), user.getAuthorities());
    }

}
