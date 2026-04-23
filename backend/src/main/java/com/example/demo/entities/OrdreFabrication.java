package com.example.demo.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data 
public class OrdreFabrication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Projet obligatoire")
    private String projet;

    @Min(value = 1, message = "Quantité invalide")
    private int quantite;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private EtatOrdre etat;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;
}