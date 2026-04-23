package com.example.demo.controllers;

import com.example.demo.dto.MachineDTO;
import com.example.demo.entities.Machine;
import com.example.demo.services.MachineService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/machines")
@CrossOrigin("*")
public class MachineController {

    @Autowired
    private MachineService service;

    @PostMapping
    public MachineDTO add(@Valid @RequestBody Machine m) {
        return MachineDTO.fromEntity(service.save(m));
    }

    @GetMapping
    public List<MachineDTO> getAll() {
        return service.getAll().stream()
                .map(MachineDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MachineDTO getById(@PathVariable Long id) {
        return MachineDTO.fromEntity(service.getById(id));
    }

    @PutMapping("/{id}")
    public MachineDTO update(@PathVariable Long id, @RequestBody Machine m) {
        return MachineDTO.fromEntity(service.update(id, m));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/panne")
    public List<MachineDTO> machinesEnPanne() {
        return service.getMachinesEnPanne().stream()
                .map(MachineDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/maintenance")
    public List<MachineDTO> machinesAEntretenir(@RequestParam String date) {
        return service.machinesAEntretenir(LocalDate.parse(date)).stream()
                .map(MachineDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
