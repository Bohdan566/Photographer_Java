package com.example.photographerjava.conrtollers;

import com.example.photographerjava.models.AuthToken;
import com.example.photographerjava.models.User;
import com.example.photographerjava.services.MailService;
import com.example.thymeleafsptingbootexample.model.Mail;
import com.example.photographerjava.services.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
public class LoginController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;

//    @PostMapping("/login")
//    public void saveUser(@RequestBody User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//    }

    @PostMapping("/register")
    public User addUser(@RequestBody User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if( userService.findUserByLogin(user.getLogin()) == null){
            userService.save(user);
            return user;
        } else

        return null;
    }

    @SneakyThrows
    @GetMapping("/email/{email}")
    public void sendEmail(@PathVariable String email){
        System.out.println(email);
        User user = userService.findUserByEmail(email);
        System.out.println(user);
        mailService.sendEmail(user);
    }


    @GetMapping("/test")
    public void test(){
        System.out.println("success !!!!!!!!!!!!!!!!!!");
    }




}
