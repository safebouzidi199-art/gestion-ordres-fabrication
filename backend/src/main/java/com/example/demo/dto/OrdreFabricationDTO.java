package com.example.demo.dto;

import com.example.demo.entities.EtatOrdre;
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
}
