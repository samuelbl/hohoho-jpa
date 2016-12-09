package br.com.hohoho.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.hohoho.modelo.CarrinhoCompra;
import br.com.hohoho.modelo.Usuario;
import br.com.hohoho.util.JPAUtil;


public class CarrinhoUserDAO {
	private static CarrinhoUserDAO instancia;
	
	public static synchronized CarrinhoUserDAO getInstance() {
		if (instancia == null) {
			instancia = new CarrinhoUserDAO();
		}
		return instancia;
	}
	
	public CarrinhoCompra verificaExistenciaCarrinho(Long userId) throws Exception{
		CarrinhoCompra carrinho= null;
		Usuario usuario = UsuarioDAO.getInstance().buscaPorId(userId);
		EntityManager manager =  new JPAUtil().getEntityManager();		
		TypedQuery<CarrinhoCompra> query = manager.createQuery("select c from CarrinhoCompra c where c.usuario = :pUsuario",CarrinhoCompra.class);
		query.setParameter("pUsuario", usuario);
		
		try {
			carrinho = query.getSingleResult();
		} catch (NoResultException ex) {
			carrinho = new CarrinhoCompra();
			carrinho.setUsuario(usuario);
			carrinho = CarrinhoDAO.getInstance().adiciona(carrinho);
			manager.close();
			return carrinho;
		}
		
//		
//		CarrinhoCompra CarrinhoResult = (CarrinhoCompra) query.getSingleResult();
//		manager.close();
//		if(CarrinhoResult != null){
//			carrinho = CarrinhoResult;
//		}
//		else{
//			carrinho = new CarrinhoCompra();
//			carrinho.setUsuario(usuario);
//			carrinho = CarrinhoDAO.getInstance().adiciona(carrinho);			
//		}
		manager.close();
		return carrinho;
	}
	
	
	
}


