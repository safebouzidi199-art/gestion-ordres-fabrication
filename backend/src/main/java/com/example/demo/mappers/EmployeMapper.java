package com.example.demo.mappers;

import com.example.demo.dto.EmployeDTO;
import com.example.demo.entities.Employe;
import com.example.demo.entities.Machine;
import org.springframework.stereotype.Component;

@Component
public class EmployeMapper {

    public EmployeDTO toDTO(Employe employe) {
        if (employe == null) {
            return null;
        }
        EmployeDTO dto = new EmployeDTO();
        dto.setId(employe.getId());
        dto.setNom(employe.getNom());
        dto.setPoste(employe.getPoste());
        if (employe.getMachineAssignee() != null) {
            dto.setMachineAssigneeId(employe.getMachineAssignee().getId());
            dto.setMachineAssigneeNom(employe.getMachineAssignee().getNom());
        }
        return dto;
    }

    public Employe toEntity(EmployeDTO dto) {
        if (dto == null) {
            return null;
        }
        Employe employe = new Employe();
        employe.setId(dto.getId());
        employe.setNom(dto.getNom());
        employe.setPoste(dto.getPoste());
        if (dto.getMachineAssigneeId() != null) {
            Machine machine = new Machine();
            machine.setId(dto.getMachineAssigneeId());
            employe.setMachineAssignee(machine);
        }
        return employe;
    }
}
