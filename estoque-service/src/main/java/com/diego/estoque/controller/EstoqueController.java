package com.diego.estoque.controller;

import com.diego.estoque.dto.EstoqueRequest;
import com.diego.estoque.model.Estoque;
import com.diego.estoque.service.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Estoque> cadastrar(@Valid @RequestBody EstoqueRequest request) {
        Estoque estoque = service.cadastrar(request);
        return ResponseEntity
                .created(URI.create("/estoques/" + estoque.getId()))
                .body(estoque);
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<Estoque> buscarPorProduto(@PathVariable Long produtoId) {
        return ResponseEntity.ok(service.buscarPorProduto(produtoId));
    }

    @PutMapping("/produto/{produtoId}/quantidade/{quantidade}")
    public ResponseEntity<Estoque> atualizarQuantidade(
            @PathVariable Long produtoId,
            @PathVariable Integer quantidade) {
        return ResponseEntity.ok(service.atualizarQuantidade(produtoId, quantidade));
    }
}
