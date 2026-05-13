package com.example.demo.services;

import com.example.demo.dto.EmployeDTO;
import com.example.demo.entities.Employe;
import com.example.demo.entities.Machine;
import com.example.demo.mappers.EmployeMapper;
import com.example.demo.repositories.EmployeRepository;
import com.example.demo.repositories.MachineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository repo;

    @Autowired
    private MachineRepository machineRepo;

    @Autowired
    private EmployeMapper mapper;

    public EmployeDTO save(EmployeDTO dto) {
        Employe employe = mapper.toEntity(dto);
        Employe saved = repo.save(employe);
        return mapper.toDTO(saved);
    }

    public List<EmployeDTO> getAll() {
        return repo.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public EmployeDTO getById(Long id) {
        Employe employe = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé introuvable"));
        return mapper.toDTO(employe);
    }

    public EmployeDTO update(Long id, EmployeDTO dto) {
        Employe e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé introuvable"));

        e.setNom(dto.getNom());
        e.setPoste(dto.getPoste());

        Employe updated = repo.save(e);
        return mapper.toDTO(updated);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // 🔥 Affectation machine
    public EmployeDTO assignMachine(Long employeId, Long machineId) {
        Employe e = repo.findById(employeId)
                .orElseThrow(() -> new RuntimeException("Employé introuvable"));
        Machine m = machineRepo.findById(machineId)
                .orElseThrow(() -> new RuntimeException("Machine introuvable"));

        e.setMachineAssignee(m);
        Employe updated = repo.save(e);
        return mapper.toDTO(updated);
    }
}