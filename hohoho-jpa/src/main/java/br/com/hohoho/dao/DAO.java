package br.com.hohoho.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.hohoho.modelo.BaseEntity;
import br.com.hohoho.util.JPAUtil;


public abstract class DAO<T extends BaseEntity> {
	private final Class<T> classe;
	public DAO(Class<T> classe) {		
		this.classe = classe;
	}

	public T adiciona(T t) throws Exception {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		if(t.getId() == null){
			manager.persist(t);	
		}
		else{
			if(!manager.contains(t)){
				throw new Exception("Erro ao atualizar o evento");
			}
			manager.merge(t);
		}
		manager.getTransaction().commit();
		manager.close();
		return t;
	}
	
	
	public List<T> listaTodos() {
		EntityManager manager = new JPAUtil().getEntityManager();
		 CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
	        query.select(query.from(classe));
	        List<T> lista = manager.createQuery(query).getResultList();
	        return lista;
	}

	public void atualiza(T t) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		manager.merge(t);
		manager.getTransaction().commit();
		manager.close();
	}

	public T buscaPorId(Long id) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		T objId = null;
		objId = manager.find(classe, id);		
		return objId;
	}
	
	

	public void remove(Long id) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		T t = manager.find(classe, id);
		manager.remove(t);
		manager.getTransaction().commit();
		manager.close();
	}

	public void remove(T t) {
		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.contains(t) ? t : manager.merge(t));
		manager.getTransaction().commit();
		manager.close();
	}
}
