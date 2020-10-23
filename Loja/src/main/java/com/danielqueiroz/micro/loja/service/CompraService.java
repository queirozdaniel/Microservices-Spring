package com.danielqueiroz.micro.loja.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielqueiroz.micro.loja.client.FornecedorClient;
import com.danielqueiroz.micro.loja.controller.dto.CompraDTO;
import com.danielqueiroz.micro.loja.controller.dto.InfoFornecedorDTO;
import com.danielqueiroz.micro.loja.controller.dto.InfoPedidoDTO;
import com.danielqueiroz.micro.loja.model.Compra;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CompraService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private FornecedorClient fornecedorClient;
	
	@HystrixCommand(fallbackMethod = "realizaCompraFallback")
	public Compra realizaCompra(CompraDTO compra) {
		final String estado = compra.getEndereco().getEstado();
		LOG.info("buscando informações do fornecedor de {}", estado);
		InfoFornecedorDTO infoFornecedor = fornecedorClient.getInfoFornecedor(estado);
		
		LOG.info("realizando um pedido");
		InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());

		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setTemporDePreparo(pedido.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		
		return compraSalva;
	}

	public Compra realizaCompraFallback(CompraDTO compra) {
		Compra compraFallback = new Compra();
		compraFallback.setEnderecoDestino(compra.getEndereco().toString());
		return compraFallback;
	}
	
}
