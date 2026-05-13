package com.example.demo.mappers;

import com.example.demo.dto.MachineDTO;
import com.example.demo.entities.Machine;
import org.springframework.stereotype.Component;

@Component
public class MachineMapper {

    public MachineDTO toDTO(Machine machine) {
        if (machine == null) {
            return null;
        }
        MachineDTO dto = new MachineDTO();
        dto.setId(machine.getId());
        dto.setNom(machine.getNom());
        dto.setEtat(machine.getEtat());
        dto.setDerniereMaintenance(machine.getDerniereMaintenance());
        return dto;
    }

    public Machine toEntity(MachineDTO dto) {
        if (dto == null) {
            return null;
        }
        Machine machine = new Machine();
        machine.setId(dto.getId());
        machine.setNom(dto.getNom());
        machine.setEtat(dto.getEtat());
        machine.setDerniereMaintenance(dto.getDerniereMaintenance());
        return machine;
    }
}
