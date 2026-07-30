package com.diego.estoque.service;

import com.diego.estoque.dto.EstoqueRequest;
import com.diego.estoque.exception.RegraNegocioException;
import com.diego.estoque.model.Estoque;
import com.diego.estoque.repository.EstoqueRepository;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    private final EstoqueRepository repository;

    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    public Estoque cadastrar(EstoqueRequest request) {
        if (repository.existsByProdutoId(request.produtoId())) {
            throw new RegraNegocioException(
                    "Estoque já cadastrado para o produto: " + request.produtoId()
            );
        }

        Estoque estoque = new Estoque(null, request.produtoId(), request.quantidade());
        return repository.save(estoque);
    }

    public Estoque buscarPorProduto(Long produtoId) {
        return repository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RegraNegocioException(
                        "Estoque não encontrado para o produto: " + produtoId
                ));
    }

    public Estoque atualizarQuantidade(Long produtoId, Integer quantidade) {
        if (quantidade < 0) {
            throw new RegraNegocioException("Quantidade não pode ser negativa");
        }

        Estoque estoque = buscarPorProduto(produtoId);
        estoque.setQuantidade(quantidade);
        return repository.save(estoque);
    }
}
