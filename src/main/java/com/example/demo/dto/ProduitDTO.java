package com.example.demo.dto;

import com.example.demo.entities.Produit;
import lombok.Data;

@Data
public class ProduitDTO {
    private Long id;
    private String nom;
    private String type;
    private int stock;
    private String fournisseur;

    public static ProduitDTO fromEntity(Produit p) {
        ProduitDTO dto = new ProduitDTO();
        dto.setId(p.getId());
        dto.setNom(p.getNom());
        dto.setType(p.getType());
        dto.setStock(p.getStock());
        dto.setFournisseur(p.getFournisseur());
        return dto;
    }
}
