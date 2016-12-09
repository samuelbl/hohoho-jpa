package br.com.hohoho.util;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.hohoho.modelo.Produto;
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
		
		m.close();
	
		//
		
		
		//produtos
		EntityManager m2 = new JPAUtil().getEntityManager();
		m2.getTransaction().begin();
		
		m2.persist(new Produto("Enfeites de árvore",
				"enfeites",
				new BigDecimal(4.50)));
		m2.persist(new Produto("Toalha de mesa",
				"toalha",
				new BigDecimal(42.20)));
		m2.persist(new Produto("Árvore de natal",
				"arvore",
				new BigDecimal(98)));
		m2.persist(new Produto("Caneca de natal cromus",
				"caneca",
				new BigDecimal(12)));
		m2.persist(new Produto("Guirlanda natalina",
				"guirlanda",
				new BigDecimal(56.50)));
		m2.persist(new Produto("Pisca-pisca 100 leds",
				"pisca",
				new BigDecimal(14.35)));
		
		//
		
	m2.getTransaction().commit();	
	
		m2.close();
		
		
		
		
		
	}
}
