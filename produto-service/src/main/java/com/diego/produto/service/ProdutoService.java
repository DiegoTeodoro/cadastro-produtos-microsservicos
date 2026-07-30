package com.diego.produto.service;

import com.diego.produto.dto.ProdutoRequest;
import com.diego.produto.exception.RegraNegocioException;
import com.diego.produto.model.Produto;
import com.diego.produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto cadastrar(ProdutoRequest request) {
        if (repository.existsByNomeIgnoreCase(request.nome())) {
            throw new RegraNegocioException("Produto já cadastrado: " + request.nome());
        }

        Produto produto = new Produto(
                null,
                request.nome(),
                request.descricao(),
                request.preco()
        );

        return repository.save(produto);
    }

    public List<Produto> listar(String nome) {
        if (nome == null || nome.isBlank()) {
            return repository.findAll();
        }
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Produto não encontrado: " + id));
    }

    public Produto atualizar(Long id, ProdutoRequest request) {
        Produto produto = buscarPorId(id);
        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        return repository.save(produto);
    }

    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        repository.delete(produto);
    }
}
