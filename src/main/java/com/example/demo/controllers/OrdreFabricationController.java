package com.example.demo.controllers;

import com.example.demo.entities.*;
import com.example.demo.services.OrdreFabricationService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordres")
@CrossOrigin("*")
public class OrdreFabricationController {

    @Autowired
    private OrdreFabricationService service;

    // 🔥 création avec logique métier
    @PostMapping
    public OrdreFabrication create(@Valid @RequestBody OrdreFabrication o) {
        return service.create(o);
    }

    @GetMapping
    public List<OrdreFabrication> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public OrdreFabrication getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // 🔥 changer état
    @PutMapping("/{id}/etat")
    public OrdreFabrication updateEtat(@PathVariable Long id, @RequestParam EtatOrdre etat) {
        return service.updateEtat(id, etat);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}