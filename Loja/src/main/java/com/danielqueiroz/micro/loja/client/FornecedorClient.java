package com.danielqueiroz.micro.loja.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.danielqueiroz.micro.loja.controller.dto.InfoFornecedorDTO;
import com.danielqueiroz.micro.loja.controller.dto.InfoPedidoDTO;
import com.danielqueiroz.micro.loja.controller.dto.ItemDaCompraDTO;

@FeignClient("fornecedor")
public interface FornecedorClient {

	@GetMapping("/info/{estado}")
	InfoFornecedorDTO getInfoFornecedor(@PathVariable String estado);

	@PostMapping("/pedido")
	InfoPedidoDTO realizaPedido(List<ItemDaCompraDTO> itens);

}
