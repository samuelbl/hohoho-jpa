package br.com.hohoho.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.hohoho.modelo.Usuario;
import br.com.hohoho.util.JPAUtil;

public class UsuarioDAO extends DAO<Usuario> {
	public static synchronized UsuarioDAO getInstance() {
		if (instancia == null) {
			instancia = new UsuarioDAO();
		}
		return instancia;
	}

	private static UsuarioDAO instancia;

	private UsuarioDAO() {
		super(Usuario.class);
	}

	public Long existeRetornaId(Usuario usuario) {
		Long retorno = null;
		EntityManager m = new JPAUtil().getEntityManager();
		Query query = m.createQuery("select u.id from Usuario u where u.email = :pEmail and senha = :pSenha");
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			retorno = (Long) query.getSingleResult();
		} catch (NoResultException ex) {
			m.close();
			return null;
		}
		m.close();
		return retorno;
		

	}
}
