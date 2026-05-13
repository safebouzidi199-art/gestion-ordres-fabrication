package com.example.demo.mappers;

import com.example.demo.dto.OrdreFabricationDTO;
import com.example.demo.entities.OrdreFabrication;
import com.example.demo.entities.Produit;
import org.springframework.stereotype.Component;

@Component
public class OrdreFabricationMapper {

    public OrdreFabricationDTO toDTO(OrdreFabrication ordre) {
        if (ordre == null) {
            return null;
        }
        OrdreFabricationDTO dto = new OrdreFabricationDTO();
        dto.setId(ordre.getId());
        dto.setProjet(ordre.getProjet());
        dto.setQuantite(ordre.getQuantite());
        dto.setDate(ordre.getDate());
        dto.setEtat(ordre.getEtat());
        if (ordre.getProduit() != null) {
            dto.setProduitId(ordre.getProduit().getId());
            dto.setProduitNom(ordre.getProduit().getNom());
        }
        return dto;
    }

    public OrdreFabrication toEntity(OrdreFabricationDTO dto) {
        if (dto == null) {
            return null;
        }
        OrdreFabrication ordre = new OrdreFabrication();
        ordre.setId(dto.getId());
        ordre.setProjet(dto.getProjet());
        ordre.setQuantite(dto.getQuantite());
        ordre.setDate(dto.getDate());
        ordre.setEtat(dto.getEtat());
        if (dto.getProduitId() != null) {
            Produit produit = new Produit();
            produit.setId(dto.getProduitId());
            ordre.setProduit(produit);
        }
        return ordre;
    }
}
