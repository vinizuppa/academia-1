//package com.daniloperez.academia.domain;
//
//import java.util.Date;
//
//import javax.persistence.Entity;
//
//import com.daniloperez.academia.domain.enums.EstadoPagamento;
//
//@Entity
//public class PagamentoViaPix extends Pagamento {
//	private static final long serialVersionUID = 1L;
//	
//	private Date dataVencimento;
//	private Date dataPagamento;
//	private String chavePix;
//	
//	public PagamentoViaPix() {
//		
//	}
//
//	public PagamentoViaPix(Integer id, EstadoPagamento estado, Matricula matricula, Date dataVencimento, Date dataPagamento, String chavePix) {
//		super(id, estado, matricula);
//		this.dataPagamento = dataPagamento;
//		this.dataVencimento = dataVencimento;
//		this.chavePix = chavePix;
//	}
//
//	public Date getDataVencimento() {
//		return dataVencimento;
//	}
//
//	public void setDataVencimento(Date dataVencimento) {
//		this.dataVencimento = dataVencimento;
//	}
//
//	public Date getDataPagamento() {
//		return dataPagamento;
//	}
//
//	public void setDataPagamento(Date dataPagamento) {
//		this.dataPagamento = dataPagamento;
//	}
//
//	public String getChavePix() {
//		return chavePix;
//	}
//
//	public void setChavePix(String chavePix) {
//		this.chavePix = chavePix;
//	}
//}
