package com.example.demo.controllers;

import com.example.demo.dto.MachineDTO;
import com.example.demo.services.MachineService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/machines")
@CrossOrigin("*")
public class MachineController {

    @Autowired
    private MachineService service;

    @PostMapping
    public MachineDTO add(@Valid @RequestBody MachineDTO dto) {
        return service.save(dto);
    }

    @GetMapping
    public List<MachineDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MachineDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public MachineDTO update(@PathVariable Long id, @RequestBody MachineDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/panne")
    public List<MachineDTO> machinesEnPanne() {
        return service.getMachinesEnPanne();
    }

    @GetMapping("/maintenance")
    public List<MachineDTO> machinesAEntretenir(@RequestParam String date) {
        return service.machinesAEntretenir(LocalDate.parse(date));
    }
}
