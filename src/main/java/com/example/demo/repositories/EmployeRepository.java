package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Employe;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

    // rechercher par nom
    List<Employe> findByNomContaining(String nom);

    // employés affectés à une machine
    List<Employe> findByMachineAssigneeId(Long machineId);
}