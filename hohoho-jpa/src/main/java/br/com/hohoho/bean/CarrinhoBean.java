package br.com.hohoho.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.hohoho.dao.CarrinhoDAO;
import br.com.hohoho.modelo.CarrinhoCompra;
import br.com.hohoho.modelo.ItemComercial;

@ManagedBean
@ViewScoped

public class CarrinhoBean {
	FacesContext context = FacesContext.getCurrentInstance();
	private CarrinhoCompra carrinho;
	
	public CarrinhoBean(){
		carrinho = (CarrinhoCompra) context.getExternalContext().getSessionMap()
				.get("carrinhoCompra");	
	}
	
	public void removerDoCarrinho(ItemComercial itemRemocao) {
		if(itemRemocao.getQuantidadeSelecionadaRemocao() == itemRemocao.getQuantidade()){
			CarrinhoDAO.getInstance().removeDoCarrinho(itemRemocao, this.carrinho);			
		}
		else{		
			CarrinhoDAO.getInstance().removeQuantidadeDoCarrinho(itemRemocao, this.carrinho);
		}
	}

	public CarrinhoCompra getCarrinho() {
		return carrinho;
	}
	
	
	public List<Integer> quantidadeDoItem(ItemComercial item){		
		List<Integer> quantidadesPossiveisDoItem = new ArrayList<>();
		for(int i=1;i<=item.getQuantidade();i++){
			quantidadesPossiveisDoItem.add(i);
		}
		return quantidadesPossiveisDoItem;
	}

	public void setCarrinho(CarrinhoCompra carrinho) {
		this.carrinho = carrinho;
	}



	

}
