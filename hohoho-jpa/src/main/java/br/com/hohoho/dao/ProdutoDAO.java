package br.com.hohoho.dao;

import java.math.BigDecimal;

import br.com.hohoho.modelo.Produto;

public class ProdutoDAO extends DAO<Produto> {
	public static synchronized ProdutoDAO getInstance() {
		if (instancia == null) {
			instancia = new ProdutoDAO();
		}
		return instancia;
	}

	private static ProdutoDAO instancia;

	private ProdutoDAO() {
		super(Produto.class);
		geraDados();
	}

	@Override
	void geraDados() {
		geraIdEAdiciona(new Produto("Enfeites de árvore",
				"enfeites",
				new BigDecimal(4.50)));
		geraIdEAdiciona(new Produto("Toalha de mesa",
				"toalha",
				new BigDecimal(42.20)));
		geraIdEAdiciona(new Produto("Árvore de natal",
				"arvore",
				new BigDecimal(98)));
		geraIdEAdiciona(new Produto("Caneca de natal cromus",
				"caneca",
				new BigDecimal(12)));
		geraIdEAdiciona(new Produto("Guirlanda natalina",
				"guirlanda",
				new BigDecimal(56.50)));
		geraIdEAdiciona(new Produto("Pisca-pisca 100 leds",
				"pisca",
				new BigDecimal(14.35)));
	}
}
