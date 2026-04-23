package com.example.demo.services;

import com.example.demo.entities.Produit;
import com.example.demo.repositories.ProduitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository repo;

    public Produit save(Produit p) {
        if (p.getStock() < 0) {
            throw new RuntimeException("Stock invalide");
        }
        return repo.save(p);
    }

    public List<Produit> getAll() {
        return repo.findAll();
    }

    public Produit getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
    }

    public Produit update(Long id, Produit newP) {
        Produit p = getById(id);

        p.setNom(newP.getNom());
        p.setType(newP.getType());
        p.setStock(newP.getStock());
        p.setFournisseur(newP.getFournisseur());

        return repo.save(p);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // 🔥 gestion stock
    public void diminuerStock(Long id, int qte) {
        Produit p = getById(id);

        if (p.getStock() < qte) {
            throw new RuntimeException("Stock insuffisant");
        }

        p.setStock(p.getStock() - qte);
        repo.save(p);
    }
}