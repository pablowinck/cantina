package com.github.pablowinck.cantina.core.entity.repository;

import com.github.pablowinck.cantina.core.entity.Produto;

import java.util.Optional;

public interface ProdutoRepository {

	Optional<Produto> findById(String id);

	Produto save(Produto produto);

}
