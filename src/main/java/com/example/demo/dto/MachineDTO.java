package com.example.demo.dto;

import com.example.demo.entities.Machine;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MachineDTO {
    private Long id;
    private String nom;
    private String etat;
    private LocalDate derniereMaintenance;

    public static MachineDTO fromEntity(Machine m) {
        MachineDTO dto = new MachineDTO();
        dto.setId(m.getId());
        dto.setNom(m.getNom());
        dto.setEtat(m.getEtat());
        dto.setDerniereMaintenance(m.getDerniereMaintenance());
        return dto;
    }
}
