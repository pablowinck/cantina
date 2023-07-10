package com.github.pablowinck.cantina.core.entity.repository;

import com.github.pablowinck.cantina.core.entity.Compra;

import java.util.List;

public interface CompraRepository {

	Compra save(Compra compra);

	List<Compra> findAllByUsuarioId(String usuarioId);
}
