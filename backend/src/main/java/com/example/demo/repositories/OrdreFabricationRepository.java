package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.OrdreFabrication;
import com.example.demo.entities.EtatOrdre;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface OrdreFabricationRepository extends JpaRepository<OrdreFabrication, Long> {

    // rechercher par état
    List<OrdreFabrication> findByEtat(EtatOrdre etat);

    // rechercher par produit
    List<OrdreFabrication> findByProduitId(Long produitId);

    // rechercher par date
    List<OrdreFabrication> findByDate(LocalDate date);

    // ordres entre deux dates
    List<OrdreFabrication> findByDateBetween(LocalDate start, LocalDate end);
}