package br.com.hohoho.dao;

import java.util.HashMap;
import java.util.Map;

import br.com.hohoho.modelo.CarrinhoCompra;


public class CarrinhoUserDAO {
	private final static Map<Long, CarrinhoCompra> CARRINHO = new HashMap<>();
	private static CarrinhoUserDAO instancia;
	
	public static synchronized CarrinhoUserDAO getInstance() {
		if (instancia == null) {
			instancia = new CarrinhoUserDAO();
		}
		return instancia;
	}
	
	public CarrinhoCompra verificaExistenciaCarrinho(Long userId){
		CarrinhoCompra carrinho= null;
		if(CARRINHO.containsKey(userId)){
			carrinho = CARRINHO.get(userId);
		}
		else{
			carrinho = new CarrinhoCompra();
			carrinho = CarrinhoDAO.getInstance().adiciona(carrinho);
			CARRINHO.put(userId, carrinho);			
		}
		
		return carrinho;
	}
	
	
	
}


