package com.danielqueiroz.micro.fornecedor.repository;

import org.springframework.data.repository.CrudRepository;

import com.danielqueiroz.micro.fornecedor.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
