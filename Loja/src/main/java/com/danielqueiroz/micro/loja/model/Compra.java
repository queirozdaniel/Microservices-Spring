package com.danielqueiroz.micro.loja.model;

import java.time.LocalDate;

public class Compra {

	private Long pedidoId;

	private Integer temporDePreparo;

	private String enderecoDestino;
	
	private LocalDate dataParaEntrega;
	
	private Long voucher;

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

	public LocalDate getDataParaEntrega() {
		return dataParaEntrega;
	}

	public void setDataParaEntrega(LocalDate dataParaEntrega) {
		this.dataParaEntrega = dataParaEntrega;
	}

	public Long getVoucher() {
		return voucher;
	}

	public void setVoucher(Long voucher) {
		this.voucher = voucher;
	}
	
	

}
