package com.github.pablowinck.cantina.core.usecase;

import com.github.pablowinck.cantina.core.dto.AbrirCompraCommand;
import com.github.pablowinck.cantina.core.entity.Compra;
import com.github.pablowinck.cantina.core.entity.repository.CompraRepository;
import com.github.pablowinck.cantina.core.entity.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class AbreCompra {

	private final CompraRepository compraRepository;

	private final ProdutoRepository produtoRepository;

	@Transactional
	public void execute(AbrirCompraCommand command) {
		log.info("Abrindo compra: {}", command);
		Compra compraSalva = this.compraRepository.save(command.toEntity());
		Optional.ofNullable(command.getProdutoId())
			.flatMap(produtoRepository::findById)
			.ifPresentOrElse(compraSalva::addProduto, () -> {
				throw new RuntimeException("Produto não encontrado");
			});
		log.info("Compra aberta: {}", compraSalva);
	}

}
