package com.example.photographerjava.services;

import com.example.photographerjava.dao.UserDAO;
import com.example.photographerjava.models.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("udsi")
@AllArgsConstructor
public class UserDetailServiceImplement implements UserDetailsService {

    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userDAO.findUserByLogin(login);
    }


}
