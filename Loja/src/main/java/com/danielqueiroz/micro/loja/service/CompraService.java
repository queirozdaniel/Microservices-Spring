package com.danielqueiroz.micro.loja.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danielqueiroz.micro.loja.controller.dto.CompraDTO;
import com.danielqueiroz.micro.loja.controller.dto.InfoFonecedorDTO;

@Service
public class CompraService {

	public void realizaCompra(CompraDTO compra) {
		
		RestTemplate client = new RestTemplate();
		ResponseEntity<InfoFonecedorDTO> exchange = client.exchange(
					"http://localhost:8081/info/" + compra.getEndereco().getEstado(), 
					HttpMethod.GET,
					null, 
					InfoFonecedorDTO.class);
		
		System.out.println(exchange.getBody().getEndereco());

	}

}
