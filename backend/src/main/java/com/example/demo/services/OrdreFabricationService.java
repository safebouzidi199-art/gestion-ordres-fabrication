package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdreFabricationService {

    @Autowired
    private OrdreFabricationRepository repo;

    @Autowired
    private ProduitRepository produitRepo;

    public OrdreFabrication create(OrdreFabrication o) {

        Produit p = produitRepo.findById(o.getProduit().getId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        // 🔥 logique métier
        if (p.getStock() < o.getQuantite()) {
            throw new RuntimeException("Stock insuffisant");
        }

        // diminuer stock
        p.setStock(p.getStock() - o.getQuantite());
        produitRepo.save(p);

        o.setEtat(EtatOrdre.EN_ATTENTE);

        return repo.save(o);
    }

    public List<OrdreFabrication> getAll() {
        return repo.findAll();
    }

    public OrdreFabrication getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordre introuvable"));
    }

    public OrdreFabrication updateEtat(Long id, EtatOrdre etat) {
        OrdreFabrication o = getById(id);
        o.setEtat(etat);
        return repo.save(o);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}