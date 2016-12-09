package br.com.hohoho.modelo;

import java.math.BigDecimal;

public class Venda {
	private Produto produto;
	private Integer quantidade;
	private BigDecimal total;
	
	public Venda(Produto produto, Integer quantidade, BigDecimal total) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.total = total;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
