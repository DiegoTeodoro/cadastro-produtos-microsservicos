package com.diego.produto.service;

import com.diego.produto.dto.ProdutoRequest;
import com.diego.produto.exception.RegraNegocioException;
import com.diego.produto.model.Produto;
import com.diego.produto.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    private ProdutoRepository repository;
    private ProdutoService service;

    @BeforeEach
    void configurar() {
        repository = Mockito.mock(ProdutoRepository.class);
        service = new ProdutoService(repository);
    }

    @Test
    void deveCadastrarProduto() {
        ProdutoRequest request =
                new ProdutoRequest("Teclado", "Teclado mecânico", new BigDecimal("250.00"));

        when(repository.existsByNomeIgnoreCase("Teclado")).thenReturn(false);
        when(repository.save(any(Produto.class))).thenAnswer(invocation -> {
            Produto produto = invocation.getArgument(0);
            produto.setId(1L);
            return produto;
        });

        Produto resultado = service.cadastrar(request);

        assertEquals(1L, resultado.getId());
        assertEquals("Teclado", resultado.getNome());
        verify(repository).save(any(Produto.class));
    }

    @Test
    void naoDeveCadastrarNomeDuplicado() {
        ProdutoRequest request =
                new ProdutoRequest("Teclado", null, new BigDecimal("250.00"));

        when(repository.existsByNomeIgnoreCase("Teclado")).thenReturn(true);

        RegraNegocioException exception = assertThrows(
                RegraNegocioException.class,
                () -> service.cadastrar(request)
        );

        assertEquals("Produto já cadastrado: Teclado", exception.getMessage());
        verify(repository, never()).save(any());
    }
}
