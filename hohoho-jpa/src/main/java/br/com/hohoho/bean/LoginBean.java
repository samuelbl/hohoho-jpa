package br.com.hohoho.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.hohoho.dao.CarrinhoUserDAO;
import br.com.hohoho.modelo.CarrinhoCompra;
import br.com.hohoho.dao.UsuarioDAO;
import br.com.hohoho.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();

	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}

	public String efetuaLogin() throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		Long existe = UsuarioDAO.getInstance().existeRetornaId(this.usuario);
		if (existe!= null) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			CarrinhoCompra carrinho = CarrinhoUserDAO.getInstance().verificaExistenciaCarrinho(existe);
			context.getExternalContext().getSessionMap().put("carrinhoCompra", carrinho);
			return "loja?faces-redirect=true";
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado"));
		return "login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
