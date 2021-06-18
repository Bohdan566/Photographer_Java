package com.example.photographerjava.dao;

import com.example.photographerjava.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {

    User findUserByLogin(String login);

    User findUserByEmail(String email);

}
