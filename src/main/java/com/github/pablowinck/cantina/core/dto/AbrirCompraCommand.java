package com.github.pablowinck.cantina.core.dto;

import com.github.pablowinck.cantina.core.entity.Compra;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
public class AbrirCompraCommand {

	@Getter
	private String produtoId;

	private String usuarioId;

	public Compra toEntity() {
		return Compra.builder().usuarioId(usuarioId).build();
	}

}
