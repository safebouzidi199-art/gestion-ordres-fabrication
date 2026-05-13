package com.example.demo.controllers;

import com.example.demo.dto.EmployeDTO;
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
    public EmployeDTO add(@Valid @RequestBody EmployeDTO dto) {
        return service.save(dto);
    }

    @GetMapping
    public List<EmployeDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EmployeDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public EmployeDTO update(@PathVariable Long id, @RequestBody EmployeDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{empId}/machine/{machineId}")
    public EmployeDTO assignMachine(@PathVariable Long empId, @PathVariable Long machineId) {
        return service.assignMachine(empId, machineId);
    }
}
