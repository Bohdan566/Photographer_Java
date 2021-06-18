package com.example.photographerjava.conrtollers;

import com.example.photographerjava.models.User;
import com.example.photographerjava.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class ActivationController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/activate/{id}")
    public String activateUser(@PathVariable int id, Model model){
        User userById = userService.findUserById(id);
        userById.setEnabled(true);
        userService.save(userById);
        model.addAttribute("user", userById);
        model.addAttribute("id", userById.getId());
        return "redirect:http://localhost:4200/users/{id}";
//        return "SuccessActivation.html";
    }

    @PostMapping("/login")
    public String saveUser(@RequestBody User user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userByLogin = userService.findUserByLogin(user.getLogin());
        if (user == userByLogin) {
            userService.save(userByLogin);
            model.addAttribute("user", userByLogin);
            model.addAttribute("id", userByLogin.getId());
            return "redirect:http://localhost:4200/users/{id}";
        } else {
            return null;
        }
    }
}
