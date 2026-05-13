package com.example.demo.controllers;

import com.example.demo.dto.OrdreFabricationDTO;
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

    @PostMapping
    public OrdreFabricationDTO create(@Valid @RequestBody OrdreFabricationDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<OrdreFabricationDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public OrdreFabricationDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}/etat")
    public OrdreFabricationDTO updateEtat(@PathVariable Long id, @RequestParam EtatOrdre etat) {
        return service.updateEtat(id, etat);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
