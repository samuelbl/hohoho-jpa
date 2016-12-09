package br.com.hohoho.dao;


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
	}
	
}
