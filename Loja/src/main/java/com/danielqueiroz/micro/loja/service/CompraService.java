package com.danielqueiroz.micro.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danielqueiroz.micro.loja.controller.dto.CompraDTO;
import com.danielqueiroz.micro.loja.controller.dto.InfoFonecedorDTO;

@Service
public class CompraService {
	
	@Autowired
	private RestTemplate client;

	@Autowired
	private DiscoveryClient eurekaClient;
	
	public void realizaCompra(CompraDTO compra) {

		ResponseEntity<InfoFonecedorDTO> exchange = client.exchange(
					"http://fornecedor/info/" + compra.getEndereco().getEstado(), 
					HttpMethod.GET,
					null, 
					InfoFonecedorDTO.class);
		
		eurekaClient.getInstances("fornecedor").stream().forEach(fornecedor -> System.out.println("localhost:"+fornecedor.getPort()));
		
		System.out.println(exchange.getBody().getEndereco());
	}

}
