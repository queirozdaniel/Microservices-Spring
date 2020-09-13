package com.danielqueiroz.micro.loja.model;

public class Compra {

	private Long pedidoId;

	private Integer temporDePreparo;

	private String enderecoDestino;

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Integer getTemporDePreparo() {
		return temporDePreparo;
	}

	public void setTemporDePreparo(Integer temporDePreparo) {
		this.temporDePreparo = temporDePreparo;
	}

	public String getEnderecoDestino() {
		return enderecoDestino;
	}

	public void setEnderecoDestino(String enderecoDestino) {
		this.enderecoDestino = enderecoDestino;
	}

}
