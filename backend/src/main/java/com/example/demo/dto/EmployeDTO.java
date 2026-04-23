package com.example.demo.dto;

import com.example.demo.entities.Employe;
import lombok.Data;

@Data
public class EmployeDTO {
    private Long id;
    private String nom;
    private String poste;
    private Long machineAssigneeId;
    private String machineAssigneeNom;

    public static EmployeDTO fromEntity(Employe e) {
        EmployeDTO dto = new EmployeDTO();
        dto.setId(e.getId());
        dto.setNom(e.getNom());
        dto.setPoste(e.getPoste());
        if (e.getMachineAssignee() != null) {
            dto.setMachineAssigneeId(e.getMachineAssignee().getId());
            dto.setMachineAssigneeNom(e.getMachineAssignee().getNom());
        }
        return dto;
    }
}
