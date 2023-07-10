package com.github.pablowinck.cantina.outbound.jpa;

import com.github.pablowinck.cantina.core.entity.Produto;
import com.github.pablowinck.cantina.core.entity.repository.ProdutoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoJPARepository extends ProdutoRepository, JpaRepository<Produto, String> {

}
