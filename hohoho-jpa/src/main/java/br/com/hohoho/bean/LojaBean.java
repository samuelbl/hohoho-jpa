package br.com.hohoho.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.hohoho.dao.CarrinhoDAO;
import br.com.hohoho.dao.ItemComercialDAO;
import br.com.hohoho.modelo.CarrinhoCompra;
import br.com.hohoho.modelo.ItemComercial;
import br.com.hohoho.dao.ProdutoDAO;
import br.com.hohoho.modelo.Produto;

@ManagedBean
@ViewScoped
public class LojaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Produto produto = new Produto();
	private List<Produto> produtos = ProdutoDAO.getInstance().listaTodos();
	private Integer quantidadeSelecionada;
	
	public Produto getProduto() {
		return produto;
	}
	
	public static List<Integer> getQuantidadeOpt() {
		List<Integer> quantidades = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			quantidades.add(i);			
		}
		return quantidades;
	}

	public List<Produto> getProdutos(){
		return this.produtos;
	}

	public Integer getQuantidadeSelecionada() {
		return quantidadeSelecionada;
	}

	public void setQuantidadeSelecionada(Integer quantidadeSelecionada) {
		this.quantidadeSelecionada = quantidadeSelecionada;
	}
	
	public String adicionarAoCarrinho() throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		
		String idProduto = (String)context.getExternalContext().getRequestParameterMap().get("idProduto");
		Produto produto = ProdutoDAO.getInstance().buscaPorId(Long.valueOf(idProduto));
		ItemComercial item = new ItemComercial(produto, this.getQuantidadeSelecionada());
		item = ItemComercialDAO.getInstance().adiciona(item);
		CarrinhoCompra carrinho = (CarrinhoCompra) context.getExternalContext().getSessionMap().get("carrinhoCompra");
		boolean controle = false;
		for (ItemComercial itemComercial : carrinho.getItens()) {
			if (itemComercial.getProduto().getId().equals(item.getProduto().getId())){
				itemComercial.setQuantidade(itemComercial.getQuantidade()+item.getQuantidade());
				itemComercial.setTotal(itemComercial.getTotal().add(item.getTotal()));
				ItemComercialDAO.getInstance().atualiza(itemComercial);
				controle = true;
			}
		}
		if(!controle){
			carrinho.setItens(item);
		}
		CarrinhoDAO.getInstance().atualizaCarrinhoEtotal(carrinho);
		return "carrinho?faces-redirect=true";
	}
	

}