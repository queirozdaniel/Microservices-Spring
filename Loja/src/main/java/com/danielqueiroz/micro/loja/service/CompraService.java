package com.danielqueiroz.micro.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielqueiroz.micro.loja.client.FornecedorClient;
import com.danielqueiroz.micro.loja.controller.dto.CompraDTO;
import com.danielqueiroz.micro.loja.controller.dto.InfoFornecedorDTO;
import com.danielqueiroz.micro.loja.controller.dto.InfoPedidoDTO;
import com.danielqueiroz.micro.loja.model.Compra;

@Service
public class CompraService {
	
	@Autowired
	private FornecedorClient fornecedorClient;
	
	public Compra realizaCompra(CompraDTO compra) {
		InfoFornecedorDTO infoFornecedor = fornecedorClient.getInfoFornecedor(compra.getEndereco().getEstado());
		
		InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
		
		System.out.println(infoFornecedor.getEndereco());

		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setTemporDePreparo(pedido.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		
		return compraSalva;
	}

}
