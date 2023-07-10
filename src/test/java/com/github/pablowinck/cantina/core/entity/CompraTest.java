package com.github.pablowinck.cantina.core.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CompraTest {

	@Test
	@DisplayName("Caso lista de produtos seja nula, então quando der addProduto não deve lançar exception e sim instanciar o atributo")
	void casoListaDeProdutosSejaNulaEntaoQuandoDerAddProdutoNaoDeveLancarExceptionESimInstanciarOAtributo() {
		// given
		Compra compra = Compra.builder().build();
		Produto produto = Produto.builder().build();

		// when
		assertDoesNotThrow(() -> compra.addProduto(produto));

		// then
		assertNotNull(compra.getProdutos());
		assertTrue(compra.getProdutos().contains(produto));
	}

	@Test
	@DisplayName("Deve somar o total dos produtos")
	void deveSomarOTotalDosProdutos() {
		// given
		Compra compra = Compra.builder().build();
		Produto produto1 = Produto.builder().preco(BigDecimal.valueOf(10.0)).build();
		Produto produto2 = Produto.builder().preco(BigDecimal.valueOf(20.0)).build();
		compra.addProduto(produto1);
		compra.addProduto(produto2);

		// when
		BigDecimal total = compra.getTotal();

		// then
		assertEquals(BigDecimal.valueOf(30.0), total);
	}

}