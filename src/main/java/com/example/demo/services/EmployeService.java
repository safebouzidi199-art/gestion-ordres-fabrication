package com.example.demo.services;

import com.example.demo.entities.Employe;
import com.example.demo.entities.Machine;
import com.example.demo.repositories.EmployeRepository;
import com.example.demo.repositories.MachineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository repo;

    @Autowired
    private MachineRepository machineRepo;

    public Employe save(Employe e) {
        return repo.save(e);
    }

    public List<Employe> getAll() {
        return repo.findAll();
    }

    public Employe getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé introuvable"));
    }

    public Employe update(Long id, Employe newE) {
        Employe e = getById(id);

        e.setNom(newE.getNom());
        e.setPoste(newE.getPoste());

        return repo.save(e);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // 🔥 Affectation machine
    public Employe assignMachine(Long employeId, Long machineId) {
        Employe e = getById(employeId);
        Machine m = machineRepo.findById(machineId)
                .orElseThrow(() -> new RuntimeException("Machine introuvable"));

        e.setMachineAssignee(m);
        return repo.save(e);
    }
}