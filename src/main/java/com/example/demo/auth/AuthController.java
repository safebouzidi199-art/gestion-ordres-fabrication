package com.example.demo.auth;

import com.example.demo.entities.Utilisateur;
import com.example.demo.repositories.UtilisateurRepository;
import com.example.demo.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UtilisateurRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest req) {
        Utilisateur user = new Utilisateur();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setNom(req.getNom());
        user.setRole(req.getRole());
        repo.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getRole().name(), user.getUsername());
    }

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
