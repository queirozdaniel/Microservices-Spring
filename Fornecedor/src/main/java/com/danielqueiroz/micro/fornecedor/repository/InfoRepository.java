package com.danielqueiroz.micro.fornecedor.repository;

import org.springframework.data.repository.CrudRepository;

import com.danielqueiroz.micro.fornecedor.model.InfoFornecedor;

public interface InfoRepository extends CrudRepository<InfoFornecedor, Long>{

	InfoFornecedor findByEstado(String estado);
	
}
