package br.com.hohoho.dao;

import org.apache.commons.lang3.StringUtils;

import br.com.hohoho.modelo.Usuario;

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
		geraDados();
	}

	public Long existeRetornaId(Usuario usuario) {
		Long retorno = null;
		for(Long i=0l;retorno==null && i < LISTA.size();i++){
			if (StringUtils.equalsIgnoreCase(LISTA.get(i).getEmail(), usuario.getEmail())
					&& StringUtils.equalsIgnoreCase(LISTA.get(i).getSenha(), usuario.getSenha())) {
				retorno = LISTA.get(i).getId();
			}
		}
		return retorno;
	}

	@Override
	void geraDados() {
		geraIdEAdiciona(new Usuario("admin@admin.com", "admin"));
		geraIdEAdiciona(new Usuario("teste@teste.com", "teste"));
	}
}
