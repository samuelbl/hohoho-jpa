package br.com.hohoho.util;

import javax.persistence.EntityManager;

import br.com.hohoho.modelo.Usuario;

public class PopulaBanco {

	public static void main(String[] args) {
		
		//usuario
		
		EntityManager m = new JPAUtil().getEntityManager();
		m.getTransaction().begin();
		
		Usuario usuario1 = new Usuario("bristot@gmail.com","teste");
		Usuario usuario2 = new Usuario("teste@teste.com","teste");
		Usuario usuario3 = new Usuario("professor@sematec.com","teste");
		
		m.persist(usuario1);
		m.persist(usuario2);
		m.persist(usuario3);
		
		m.getTransaction().commit();
		
		//
		
		
		m.close();
		
		
	}
}
