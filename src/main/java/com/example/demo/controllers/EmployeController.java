package com.example.demo.controllers;

import com.example.demo.dto.EmployeDTO;
import com.example.demo.entities.Employe;
import com.example.demo.services.EmployeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employes")
@CrossOrigin("*")
public class EmployeController {

    @Autowired
    private EmployeService service;

    @PostMapping
    public EmployeDTO add(@Valid @RequestBody Employe e) {
        return EmployeDTO.fromEntity(service.save(e));
    }

    @GetMapping
    public List<EmployeDTO> getAll() {
        return service.getAll().stream()
                .map(EmployeDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeDTO getById(@PathVariable Long id) {
        return EmployeDTO.fromEntity(service.getById(id));
    }

    @PutMapping("/{id}")
    public EmployeDTO update(@PathVariable Long id, @RequestBody Employe e) {
        return EmployeDTO.fromEntity(service.update(id, e));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{empId}/machine/{machineId}")
    public EmployeDTO assignMachine(@PathVariable Long empId, @PathVariable Long machineId) {
        return EmployeDTO.fromEntity(service.assignMachine(empId, machineId));
    }
}
