package com.danielqueiroz.micro.loja.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielqueiroz.micro.loja.client.FornecedorClient;
import com.danielqueiroz.micro.loja.client.TransportadorClient;
import com.danielqueiroz.micro.loja.controller.dto.CompraDTO;
import com.danielqueiroz.micro.loja.controller.dto.InfoEntregaDTO;
import com.danielqueiroz.micro.loja.controller.dto.InfoFornecedorDTO;
import com.danielqueiroz.micro.loja.controller.dto.InfoPedidoDTO;
import com.danielqueiroz.micro.loja.controller.dto.VoucherDTO;
import com.danielqueiroz.micro.loja.model.Compra;
import com.danielqueiroz.micro.loja.repository.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CompraService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private FornecedorClient fornecedorClient;
	
	@Autowired
	private TransportadorClient transportadorClient;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@HystrixCommand(fallbackMethod = "realizaCompraFallback")
	public Compra realizaCompra(CompraDTO compra) {
		final String estado = compra.getEndereco().getEstado();
		LOG.info("buscando informações do fornecedor de {}", estado);
		InfoFornecedorDTO infoFornecedor = fornecedorClient.getInfoFornecedor(estado);
		
		LOG.info("realizando um pedido");
		InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
		
		InfoEntregaDTO entregaDto = new InfoEntregaDTO();
		entregaDto.setPedidoId(pedido.getId());
		entregaDto.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
		entregaDto.setEnderecoOrigem(infoFornecedor.getEndereco());
		entregaDto.setEnderecoDestino(compra.getEndereco().toString());
		VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDto);

		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setTemporDePreparo(pedido.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		compraSalva.setVoucher(voucher.getNumero());
		compraSalva.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
		compraRepository.save(compraSalva);
		
		return compraSalva;
	}

	public Compra realizaCompraFallback(CompraDTO compra) {
		Compra compraFallback = new Compra();
		compraFallback.setEnderecoDestino(compra.getEndereco().toString());
		return compraFallback;
	}
	
}
