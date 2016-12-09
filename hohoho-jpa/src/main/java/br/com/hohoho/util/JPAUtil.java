package br.com.hohoho.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("hohoho");

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}
