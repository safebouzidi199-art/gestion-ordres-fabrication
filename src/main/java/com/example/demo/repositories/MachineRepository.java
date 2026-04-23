package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Machine;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    // rechercher par état (ex: disponible, en panne)
    List<Machine> findByEtat(String etat);

    // rechercher machines avec maintenance avant une date
    List<Machine> findByDerniereMaintenanceBefore(java.time.LocalDate date);
}