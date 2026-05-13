package com.example.demo.services;

import com.example.demo.dto.MachineDTO;
import com.example.demo.entities.Machine;
import com.example.demo.mappers.MachineMapper;
import com.example.demo.repositories.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MachineService {

    @Autowired
    private MachineRepository repo;

    @Autowired
    private MachineMapper mapper;

    public MachineDTO save(MachineDTO dto) {
        if (dto.getNom() == null || dto.getNom().isEmpty()) {
            throw new RuntimeException("Nom machine obligatoire");
        }
        Machine machine = mapper.toEntity(dto);
        Machine saved = repo.save(machine);
        return mapper.toDTO(saved);
    }

    public List<MachineDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public MachineDTO getById(Long id) {
        Machine machine = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Machine introuvable"));
        return mapper.toDTO(machine);
    }

    public MachineDTO update(Long id, MachineDTO dto) {
        Machine m = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Machine introuvable"));

        m.setNom(dto.getNom());
        m.setEtat(dto.getEtat());
        m.setDerniereMaintenance(dto.getDerniereMaintenance());

        Machine updated = repo.save(m);
        return mapper.toDTO(updated);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // Bonus métier
    public List<MachineDTO> getMachinesEnPanne() {
        return repo.findByEtat("EN_PANNE").stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<MachineDTO> machinesAEntretenir(LocalDate date) {
        return repo.findByDerniereMaintenanceBefore(date).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}