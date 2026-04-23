package com.example.demo.services;

import com.example.demo.entities.Produit;
import com.example.demo.repositories.ProduitRepository;
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
class ProduitServiceTest {

    @Mock
    private ProduitRepository repo;

    @InjectMocks
    private ProduitService service;

    private Produit produit;

    @BeforeEach
    void setUp() {
        produit = new Produit();
        produit.setId(1L);
        produit.setNom("Acier");
        produit.setType("Matière première");
        produit.setStock(100);
        produit.setFournisseur("FournisseurA");
    }

    @Test
    void save_shouldPersistProduit() {
        when(repo.save(produit)).thenReturn(produit);
        Produit result = service.save(produit);
        assertThat(result.getNom()).isEqualTo("Acier");
        verify(repo, times(1)).save(produit);
    }

    @Test
    void save_shouldThrowWhenStockNegative() {
        produit.setStock(-5);
        assertThatThrownBy(() -> service.save(produit))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Stock invalide");
    }

    @Test
    void getAll_shouldReturnList() {
        when(repo.findAll()).thenReturn(List.of(produit));
        List<Produit> result = service.getAll();
        assertThat(result).hasSize(1);
    }

    @Test
    void getById_shouldReturnProduit() {
        when(repo.findById(1L)).thenReturn(Optional.of(produit));
        Produit result = service.getById(1L);
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void getById_shouldThrowWhenNotFound() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getById(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Produit introuvable");
    }

    @Test
    void diminuerStock_shouldReduceStock() {
        when(repo.findById(1L)).thenReturn(Optional.of(produit));
        when(repo.save(any())).thenReturn(produit);
        service.diminuerStock(1L, 30);
        assertThat(produit.getStock()).isEqualTo(70);
    }

    @Test
    void diminuerStock_shouldThrowWhenStockInsuffisant() {
        when(repo.findById(1L)).thenReturn(Optional.of(produit));
        assertThatThrownBy(() -> service.diminuerStock(1L, 200))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Stock insuffisant");
    }

    @Test
    void delete_shouldCallRepository() {
        doNothing().when(repo).deleteById(1L);
        service.delete(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}
