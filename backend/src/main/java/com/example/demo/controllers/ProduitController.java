package com.example.demo.controllers;

import com.example.demo.dto.ProduitDTO;
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
    public ProduitDTO add(@Valid @RequestBody ProduitDTO dto) {
        return service.save(dto);
    }

    @GetMapping
    public List<ProduitDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProduitDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ProduitDTO update(@PathVariable Long id, @RequestBody ProduitDTO dto) {
        return service.update(id, dto);
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
