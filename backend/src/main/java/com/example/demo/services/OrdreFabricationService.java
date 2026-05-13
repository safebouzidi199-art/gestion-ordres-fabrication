package com.example.demo.services;

import com.example.demo.dto.OrdreFabricationDTO;
import com.example.demo.entities.*;
import com.example.demo.mappers.OrdreFabricationMapper;
import com.example.demo.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdreFabricationService {

    @Autowired
    private OrdreFabricationRepository repo;

    @Autowired
    private ProduitRepository produitRepo;

    @Autowired
    private OrdreFabricationMapper mapper;

    public OrdreFabricationDTO create(OrdreFabricationDTO dto) {

        Produit p = produitRepo.findById(dto.getProduitId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        // 🔥 logique métier
        if (p.getStock() < dto.getQuantite()) {
            throw new RuntimeException("Stock insuffisant");
        }

        // diminuer stock
        p.setStock(p.getStock() - dto.getQuantite());
        produitRepo.save(p);

        OrdreFabrication ordre = mapper.toEntity(dto);
        ordre.setEtat(EtatOrdre.EN_ATTENTE);
        ordre.setProduit(p);

        OrdreFabrication saved = repo.save(ordre);
        return mapper.toDTO(saved);
    }

    public List<OrdreFabricationDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrdreFabricationDTO getById(Long id) {
        OrdreFabrication ordre = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordre introuvable"));
        return mapper.toDTO(ordre);
    }

    public OrdreFabricationDTO updateEtat(Long id, EtatOrdre etat) {
        OrdreFabrication o = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordre introuvable"));
        o.setEtat(etat);
        OrdreFabrication updated = repo.save(o);
        return mapper.toDTO(updated);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}