package com.example.demo.controllers;

import com.example.demo.entities.Produit;
import com.example.demo.services.ProduitService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
@CrossOrigin("*")
public class ProduitController {

    @Autowired
    private ProduitService service;

    @PostMapping
    public Produit add(@Valid @RequestBody Produit p) {
        return service.save(p);
    }

    @GetMapping
    public List<Produit> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Produit getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Produit update(@PathVariable Long id, @RequestBody Produit p) {
        return service.update(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // 🔥 diminuer stock
    @PutMapping("/{id}/stock")
    public void diminuerStock(@PathVariable Long id, @RequestParam int qte) {
        service.diminuerStock(id, qte);
    }
}