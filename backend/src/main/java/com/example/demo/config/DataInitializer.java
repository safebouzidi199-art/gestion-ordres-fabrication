package com.example.demo.config;

import com.example.demo.entities.Role;
import com.example.demo.entities.Utilisateur;
import com.example.demo.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UtilisateurRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (repo.findByUsername("admin").isEmpty()) {
            Utilisateur admin = new Utilisateur();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setNom("Administrateur");
            admin.setRole(Role.ADMIN);
            repo.save(admin);
            System.out.println("✅ Utilisateur admin créé : admin / admin123");
        }
        if (repo.findByUsername("responsable").isEmpty()) {
            Utilisateur resp = new Utilisateur();
            resp.setUsername("responsable");
            resp.setPassword(passwordEncoder.encode("resp123"));
            resp.setNom("Responsable Production");
            resp.setRole(Role.RESPONSABLE_PRODUCTION);
            repo.save(resp);
            System.out.println("✅ Utilisateur responsable créé : responsable / resp123");
        }
    }
}
