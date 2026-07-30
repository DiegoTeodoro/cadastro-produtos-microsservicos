package com.diego.estoque.service;

import com.diego.estoque.dto.EstoqueRequest;
import com.diego.estoque.model.Estoque;
import com.diego.estoque.repository.EstoqueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EstoqueServiceTest {

    private EstoqueRepository repository;
    private EstoqueService service;

    @BeforeEach
    void configurar() {
        repository = Mockito.mock(EstoqueRepository.class);
        service = new EstoqueService(repository);
    }

    @Test
    void deveCadastrarEstoque() {
        EstoqueRequest request = new EstoqueRequest(1L, 10);

        when(repository.existsByProdutoId(1L)).thenReturn(false);
        when(repository.save(any(Estoque.class))).thenAnswer(invocation -> {
            Estoque estoque = invocation.getArgument(0);
            estoque.setId(1L);
            return estoque;
        });

        Estoque resultado = service.cadastrar(request);

        assertEquals(1L, resultado.getProdutoId());
        assertEquals(10, resultado.getQuantidade());
        verify(repository).save(any(Estoque.class));
    }
}
