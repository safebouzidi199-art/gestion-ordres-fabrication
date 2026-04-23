package com.example.demo.auth;

import com.example.demo.entities.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String nom;
    private Role role;
}
