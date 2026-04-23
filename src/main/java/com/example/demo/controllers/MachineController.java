package com.example.demo.controllers;

import com.example.demo.entities.Machine;
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
    public Machine add(@Valid @RequestBody Machine m) {
        return service.save(m);
    }

    @GetMapping
    public List<Machine> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Machine getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Machine update(@PathVariable Long id, @RequestBody Machine m) {
        return service.update(id, m);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // 🔥 bonus
    @GetMapping("/panne")
    public List<Machine> machinesEnPanne() {
        return service.getMachinesEnPanne();
    }

    @GetMapping("/maintenance")
    public List<Machine> machinesAEntretenir(@RequestParam String date) {
        return service.machinesAEntretenir(LocalDate.parse(date));
    }
}