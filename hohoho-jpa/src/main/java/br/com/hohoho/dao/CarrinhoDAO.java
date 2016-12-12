package br.com.hohoho.dao;


import java.math.BigDecimal;

import br.com.hohoho.modelo.CarrinhoCompra;
import br.com.hohoho.modelo.ItemComercial;

public class CarrinhoDAO extends DAO<CarrinhoCompra> {
	public static synchronized CarrinhoDAO getInstance() {
		if (instancia == null) {
			instancia = new CarrinhoDAO();
		}
		return instancia;
	}

	private static CarrinhoDAO instancia;
	
	
	
	private CarrinhoDAO() {
		super(CarrinhoCompra.class);
	}
	
	public void atualizaCarrinhoEtotal(CarrinhoCompra carrinho) {
		BigDecimal total = new BigDecimal(0);
		for (ItemComercial item : carrinho.getItens()) {
			 total = total.add(item.getTotal());
		}
		carrinho.setTotal(total);
		super.atualiza(carrinho);
	}
	
	public void removeDoCarrinho(ItemComercial item, CarrinhoCompra carrinho){
		BigDecimal total = carrinho.getTotal();	
		ItemComercial itemAtac = ItemComercialDAO.getInstance().buscaPorId(item.getId());
		for (int i =0; i< carrinho.getItens().size();i++){
			ItemComercial itemCarrinho = carrinho.getItens().get(i);
			if(itemCarrinho.getId().equals(itemAtac.getId())){
				total = total.subtract(itemCarrinho.getTotal());
				carrinho.getItens().remove(itemAtac);
				ItemComercialDAO.getInstance().remove(itemAtac);
				carrinho.setTotal(total);
			}
		}
		super.atualiza(carrinho);
	}
	
	public void removeQuantidadeDoCarrinho(ItemComercial itemId, CarrinhoCompra carrinho){
		BigDecimal total = carrinho.getTotal();	
		for (int i =0; i< carrinho.getItens().size();i++){
			ItemComercial itemCarrinho = carrinho.getItens().get(i);
			if(itemCarrinho.getId().equals(itemId.getId())){
				itemCarrinho.setQuantidade(itemCarrinho.getQuantidade()-itemCarrinho.getQuantidadeSelecionadaRemocao());
				BigDecimal vlrProdutoUnitario = itemCarrinho.getProduto().getValor();
				total = total.subtract((vlrProdutoUnitario.multiply(BigDecimal.valueOf(itemCarrinho.getQuantidadeSelecionadaRemocao()))));
				itemCarrinho.setTotal(vlrProdutoUnitario.multiply(BigDecimal.valueOf(itemCarrinho.getQuantidade())));
				ItemComercialDAO.getInstance().atualiza(itemCarrinho);
				carrinho.setTotal(total);
			}
		}
		super.atualiza(carrinho);
	}		
			

	}


