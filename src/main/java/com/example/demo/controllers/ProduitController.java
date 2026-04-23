package com.example.demo.controllers;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entities.Produit;
import com.example.demo.services.ProduitService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produits")
@CrossOrigin("*")
public class ProduitController {

    @Autowired
    private ProduitService service;

    @PostMapping
    public ProduitDTO add(@Valid @RequestBody Produit p) {
        return ProduitDTO.fromEntity(service.save(p));
    }

    @GetMapping
    public List<ProduitDTO> getAll() {
        return service.getAll().stream()
                .map(ProduitDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProduitDTO getById(@PathVariable Long id) {
        return ProduitDTO.fromEntity(service.getById(id));
    }

    @PutMapping("/{id}")
    public ProduitDTO update(@PathVariable Long id, @RequestBody Produit p) {
        return ProduitDTO.fromEntity(service.update(id, p));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}/stock")
    public void diminuerStock(@PathVariable Long id, @RequestParam int qte) {
        service.diminuerStock(id, qte);
    }
}
