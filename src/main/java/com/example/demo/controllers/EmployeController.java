package com.example.demo.controllers;

import com.example.demo.entities.Employe;
import com.example.demo.services.EmployeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employes")
@CrossOrigin("*")
public class EmployeController {

    @Autowired
    private EmployeService service;

    @PostMapping
    public Employe add(@Valid @RequestBody Employe e) {
        return service.save(e);
    }

    @GetMapping
    public List<Employe> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Employe getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Employe update(@PathVariable Long id, @RequestBody Employe e) {
        return service.update(id, e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // 🔥 affectation machine
    @PutMapping("/{empId}/machine/{machineId}")
    public Employe assignMachine(@PathVariable Long empId, @PathVariable Long machineId) {
        return service.assignMachine(empId, machineId);
    }
}