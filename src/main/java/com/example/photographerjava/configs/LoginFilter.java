package com.example.photographerjava.configs;

import com.example.photographerjava.dao.UserDAO;
import com.example.photographerjava.models.AuthToken;
import com.example.photographerjava.models.User;
import com.example.photographerjava.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;

    public LoginFilter(String url, AuthenticationManager authenticationManager, UserService userService) {
        setFilterProcessesUrl(url);
        setAuthenticationManager(authenticationManager);
        this.userService = userService;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = null;

        try {
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Authentication authenticate = getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        return authenticate;
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .signWith(SignatureAlgorithm.HS512, "katy".getBytes())
                .compact();

        User user = userService.findUserByLogin(authResult.getName());

        AuthToken authToken = new AuthToken();
        authToken.setToken(token);
        authToken.setUser(user);
        user.getAuthTokens().add(authToken);
        userService.save(user);
        System.out.println(authToken);

        response.addHeader("Authorization", "Bearer " + token);
        chain.doFilter(request, response);
    }
}
