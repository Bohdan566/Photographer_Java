package com.example.photographerjava.services;

import com.example.photographerjava.dao.UserDAO;
import com.example.photographerjava.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService{

    private UserDAO userDAO;

    @Override
    public User findUserByLogin(String login) {
        return userDAO.findUserByLogin(login);
    }

    @Override
    public User save(User user) {
        User savedUser = userDAO.save(user);
        return savedUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public User getUser(String login) {
        return userDAO.findUserByLogin(login);
    }

    @Override
    public User findUserById(int id) {
        return userDAO.findById(id).orElse(null);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }
}
