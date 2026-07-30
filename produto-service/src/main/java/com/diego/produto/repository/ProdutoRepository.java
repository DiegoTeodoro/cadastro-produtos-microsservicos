package com.diego.produto.repository;

import com.diego.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByNomeIgnoreCase(String nome);
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
