package com.example.demo.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        userDetails = new User(
                "admin",
                "password",
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
    }

    @Test
    void generateToken_shouldReturnNonNullToken() {
        String token = jwtService.generateToken(userDetails);
        assertThat(token).isNotNull().isNotEmpty();
    }

    @Test
    void extractUsername_shouldReturnCorrectUsername() {
        String token = jwtService.generateToken(userDetails);
        assertThat(jwtService.extractUsername(token)).isEqualTo("admin");
    }

    @Test
    void isValid_shouldReturnTrueForValidToken() {
        String token = jwtService.generateToken(userDetails);
        assertThat(jwtService.isValid(token, userDetails)).isTrue();
    }

    @Test
    void isValid_shouldReturnFalseForWrongUser() {
        String token = jwtService.generateToken(userDetails);
        UserDetails otherUser = new User("hacker", "pass",
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        assertThat(jwtService.isValid(token, otherUser)).isFalse();
    }
}
