package com.example.demo.mappers;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entities.Produit;
import org.springframework.stereotype.Component;

@Component
public class ProduitMapper {

    public ProduitDTO toDTO(Produit produit) {
        if (produit == null) {
            return null;
        }
        ProduitDTO dto = new ProduitDTO();
        dto.setId(produit.getId());
        dto.setNom(produit.getNom());
        dto.setType(produit.getType());
        dto.setStock(produit.getStock());
        dto.setFournisseur(produit.getFournisseur());
        return dto;
    }

    public Produit toEntity(ProduitDTO dto) {
        if (dto == null) {
            return null;
        }
        Produit produit = new Produit();
        produit.setId(dto.getId());
        produit.setNom(dto.getNom());
        produit.setType(dto.getType());
        produit.setStock(dto.getStock());
        produit.setFournisseur(dto.getFournisseur());
        return produit;
    }
}
