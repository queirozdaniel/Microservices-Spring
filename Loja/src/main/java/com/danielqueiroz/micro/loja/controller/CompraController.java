package com.danielqueiroz.micro.loja.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielqueiroz.micro.loja.controller.dto.CompraDTO;

@RestController
@RequestMapping("/compra")
public class CompraController {

	@PostMapping
	public void realizaCompra(@RequestBody CompraDTO compraDto) {
		System.out.println(compraDto);
	}
	
}
