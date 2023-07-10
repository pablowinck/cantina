package com.github.pablowinck.cantina.core.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Builder.Default
	private String id = UUID.randomUUID().toString();

	@CreatedDate
	private LocalDateTime dataCriacao;

	private String usuarioId;

	@OneToMany
	@Getter
	private List<Produto> produtos;

	public BigDecimal getTotal() {
		return produtos.stream().map(Produto::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public void addProduto(Produto produto) {
		if (produtos == null)
			this.produtos = new ArrayList<>();
		this.produtos.add(produto);
	}

}
