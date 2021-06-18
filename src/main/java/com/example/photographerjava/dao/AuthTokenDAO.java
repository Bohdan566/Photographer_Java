package com.example.photographerjava.dao;

import com.example.photographerjava.models.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthTokenDAO extends JpaRepository<AuthToken, Integer> {
    AuthToken findByToken(String token);
}
