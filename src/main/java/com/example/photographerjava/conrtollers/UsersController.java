package com.example.photographerjava.conrtollers;

import com.example.photographerjava.models.User;
import com.example.photographerjava.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UsersController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id){
        User userById = userService.findUserById(id);
        return userById;
    }
}
