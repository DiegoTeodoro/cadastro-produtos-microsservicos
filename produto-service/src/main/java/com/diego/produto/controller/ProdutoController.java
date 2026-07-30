package com.diego.produto.controller;

import com.diego.produto.dto.ProdutoRequest;
import com.diego.produto.model.Produto;
import com.diego.produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@Valid @RequestBody ProdutoRequest request) {
        Produto produto = service.cadastrar(request);
        return ResponseEntity
                .created(URI.create("/produtos/" + produto.getId()))
                .body(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar(
            @RequestParam(required = false) String nome) {
        return ResponseEntity.ok(service.listar(nome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
