package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MachineDTO {
    private Long id;
    private String nom;
    private String etat;
    private LocalDate derniereMaintenance;
}
