package com.example.demo.services;

import com.example.demo.entities.Machine;
import com.example.demo.repositories.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MachineService {

    @Autowired
    private MachineRepository repo;

    public Machine save(Machine m) {
        if (m.getNom() == null || m.getNom().isEmpty()) {
            throw new RuntimeException("Nom machine obligatoire");
        }
        return repo.save(m);
    }

    public List<Machine> getAll() {
        return repo.findAll();
    }

    public Machine getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Machine introuvable"));
    }

    public Machine update(Long id, Machine newM) {
        Machine m = getById(id);

        m.setNom(newM.getNom());
        m.setEtat(newM.getEtat());
        m.setDerniereMaintenance(newM.getDerniereMaintenance());

        return repo.save(m);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // Bonus métier
    public List<Machine> getMachinesEnPanne() {
        return repo.findByEtat("EN_PANNE");
    }

    public List<Machine> machinesAEntretenir(LocalDate date) {
        return repo.findByDerniereMaintenanceBefore(date);
    }
}