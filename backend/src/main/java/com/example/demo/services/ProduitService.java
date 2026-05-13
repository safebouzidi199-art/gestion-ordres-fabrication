package com.example.demo.services;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entities.Produit;
import com.example.demo.mappers.ProduitMapper;
import com.example.demo.repositories.ProduitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository repo;

    @Autowired
    private ProduitMapper mapper;

    public ProduitDTO save(ProduitDTO dto) {
        if (dto.getStock() < 0) {
            throw new RuntimeException("Stock invalide");
        }
        Produit produit = mapper.toEntity(dto);
        Produit saved = repo.save(produit);
        return mapper.toDTO(saved);
    }

    public List<ProduitDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProduitDTO getById(Long id) {
        Produit produit = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        return mapper.toDTO(produit);
    }

    public ProduitDTO update(Long id, ProduitDTO dto) {
        Produit p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        p.setNom(dto.getNom());
        p.setType(dto.getType());
        p.setStock(dto.getStock());
        p.setFournisseur(dto.getFournisseur());

        Produit updated = repo.save(p);
        return mapper.toDTO(updated);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // 🔥 gestion stock
    public void diminuerStock(Long id, int qte) {
        Produit p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        if (p.getStock() < qte) {
            throw new RuntimeException("Stock insuffisant");
        }

        p.setStock(p.getStock() - qte);
        repo.save(p);
    }
}