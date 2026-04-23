package com.example.demo.dto;

import com.example.demo.entities.EtatOrdre;
import com.example.demo.entities.OrdreFabrication;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrdreFabricationDTO {
    private Long id;
    private String projet;
    private int quantite;
    private LocalDate date;
    private EtatOrdre etat;
    private Long produitId;
    private String produitNom;

    public static OrdreFabricationDTO fromEntity(OrdreFabrication o) {
        OrdreFabricationDTO dto = new OrdreFabricationDTO();
        dto.setId(o.getId());
        dto.setProjet(o.getProjet());
        dto.setQuantite(o.getQuantite());
        dto.setDate(o.getDate());
        dto.setEtat(o.getEtat());
        if (o.getProduit() != null) {
            dto.setProduitId(o.getProduit().getId());
            dto.setProduitNom(o.getProduit().getNom());
        }
        return dto;
    }
}
