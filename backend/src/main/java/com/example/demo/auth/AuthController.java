package com.example.demo.auth;

import com.example.demo.entities.Utilisateur;
import com.example.demo.repositories.UtilisateurRepository;
import com.example.demo.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UtilisateurRepository repo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        Utilisateur user = repo.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getRole().name(), user.getUsername());
    }
}
