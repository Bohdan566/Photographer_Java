package com.example.photographerjava.services;

import com.example.photographerjava.models.User;
import lombok.AllArgsConstructor;

import java.util.List;

public interface UserService {

    User findUserByLogin(String login);

    User save(User user);

    List<User> getAllUsers();

    User getUser(String login);

    User findUserById(int id);

    User findUserByEmail(String email);
}
