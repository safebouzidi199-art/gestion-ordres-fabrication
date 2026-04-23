package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Produit;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    // rechercher par nom
    List<Produit> findByNomContaining(String nom);

    // produits avec stock inférieur à une valeur
    List<Produit> findByStockLessThan(int stock);

    // produits par type
    List<Produit> findByType(String type);
}