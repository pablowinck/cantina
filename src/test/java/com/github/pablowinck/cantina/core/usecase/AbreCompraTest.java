package com.github.pablowinck.cantina.core.usecase;

import com.github.pablowinck.cantina.core.dto.AbrirCompraCommand;
import com.github.pablowinck.cantina.core.entity.Compra;
import com.github.pablowinck.cantina.core.entity.Produto;
import com.github.pablowinck.cantina.core.entity.Usuario;
import com.github.pablowinck.cantina.core.entity.repository.CompraRepository;
import com.github.pablowinck.cantina.core.entity.repository.ProdutoRepository;
import com.github.pablowinck.cantina.core.entity.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AbreCompraTest {

	@Autowired
	private AbreCompra abreCompra;

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Test
	@DisplayName("Deve abrir uma compra")
	@Transactional
	void deveAbrirUmaCompra() {
		// given
		var usuarioId = "123141234124214124";
		var usuario = Usuario.builder().id(usuarioId).build();
		var produto = Produto.builder().preco(BigDecimal.TEN).nome("Chocolate").build();
		usuarioRepository.save(usuario);
		Produto produtoSalvo = produtoRepository.save(produto);
		var command = AbrirCompraCommand.builder().produtoId(produtoSalvo.getId()).usuarioId(usuarioId).build();

		// when
		abreCompra.execute(command);

		// then
		List<Compra> allByUsuarioId = compraRepository.findAllByUsuarioId(usuarioId);
		assertTrue(allByUsuarioId.size() > 0);
		Compra compra = allByUsuarioId.get(0);
		assertTrue(compra.getProdutos().size() > 0);
		assertEquals(BigDecimal.TEN, compra.getTotal());
	}

	@Test
	@DisplayName("Caso não encontrar um produto, então não deve abrir uma compra")
	void casoNaoEncontrarUmProdutoEntaoNaoDeveAbrirUmaCompra() {
		// given
		var usuarioId = "123141234124214124";
		var usuario = Usuario.builder().id(usuarioId).build();
		usuarioRepository.save(usuario);
		var command = AbrirCompraCommand.builder().produtoId("123123123123123123").usuarioId(usuarioId).build();

		// when / then
		assertThrows(RuntimeException.class, () -> abreCompra.execute(command));
	}

}