package com.baratov.spring.springboot.model.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String [] roles;
    private String password;
}
