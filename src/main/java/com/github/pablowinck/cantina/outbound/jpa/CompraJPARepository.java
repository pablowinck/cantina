package com.github.pablowinck.cantina.outbound.jpa;

import com.github.pablowinck.cantina.core.entity.Compra;
import com.github.pablowinck.cantina.core.entity.repository.CompraRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraJPARepository extends CompraRepository, JpaRepository<Compra, String> {

	List<Compra> findAllByUsuarioId(String usuarioId);

}
