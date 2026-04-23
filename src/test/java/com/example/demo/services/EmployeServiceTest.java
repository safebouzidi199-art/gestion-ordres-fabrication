package com.example.demo.services;

import com.example.demo.entities.Employe;
import com.example.demo.entities.Machine;
import com.example.demo.repositories.EmployeRepository;
import com.example.demo.repositories.MachineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeServiceTest {

    @Mock
    private EmployeRepository repo;

    @Mock
    private MachineRepository machineRepo;

    @InjectMocks
    private EmployeService service;

    private Employe employe;
    private Machine machine;

    @BeforeEach
    void setUp() {
        employe = new Employe();
        employe.setId(1L);
        employe.setNom("Ali Ben Salem");
        employe.setPoste("Opérateur");

        machine = new Machine();
        machine.setId(10L);
        machine.setNom("Tour CNC");
    }

    @Test
    void save_shouldPersistEmploye() {
        when(repo.save(employe)).thenReturn(employe);
        Employe result = service.save(employe);
        assertThat(result.getNom()).isEqualTo("Ali Ben Salem");
        verify(repo).save(employe);
    }

    @Test
    void getAll_shouldReturnList() {
        when(repo.findAll()).thenReturn(List.of(employe));
        assertThat(service.getAll()).hasSize(1);
    }

    @Test
    void getById_shouldReturnEmploye() {
        when(repo.findById(1L)).thenReturn(Optional.of(employe));
        assertThat(service.getById(1L).getNom()).isEqualTo("Ali Ben Salem");
    }

    @Test
    void getById_shouldThrowWhenNotFound() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getById(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Employé introuvable");
    }

    @Test
    void assignMachine_shouldSetMachine() {
        when(repo.findById(1L)).thenReturn(Optional.of(employe));
        when(machineRepo.findById(10L)).thenReturn(Optional.of(machine));
        when(repo.save(employe)).thenReturn(employe);

        Employe result = service.assignMachine(1L, 10L);
        assertThat(result.getMachineAssignee().getNom()).isEqualTo("Tour CNC");
    }

    @Test
    void assignMachine_shouldThrowWhenMachineNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.of(employe));
        when(machineRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.assignMachine(1L, 99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Machine introuvable");
    }

    @Test
    void delete_shouldCallRepository() {
        doNothing().when(repo).deleteById(1L);
        service.delete(1L);
        verify(repo).deleteById(1L);
    }
}
