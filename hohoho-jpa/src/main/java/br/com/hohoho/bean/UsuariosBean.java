package br.com.hohoho.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;

import br.com.hohoho.dao.UsuarioDAO;
import br.com.hohoho.modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuariosBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = UsuarioDAO.getInstance().listaTodos();


	public Usuario getUsuario() {
		return usuario;
	}
	
	public String cadastrarUsuario() throws Exception{
		String retorno = "";
		FacesContext context = FacesContext.getCurrentInstance();
		if (UsuarioDAO.getInstance().existeRetornaId(usuario) != null){
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Usuario já existe!"));
			retorno = "usuarios?faces-redirect=true";
			
		}
		else{
			UsuarioDAO.getInstance().adiciona(this.usuario);
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Usuario cadastrado com sucesso"));
			retorno = "login?faces-redirect=true";
		}		
		return retorno;
	}
	
	public String removerUsuario() {
		FacesContext context = FacesContext.getCurrentInstance();
		String idUsuario = (String) context.getExternalContext().getRequestParameterMap().get("idUsuario");
		UsuarioDAO.getInstance().remove(Long.valueOf(idUsuario));
		return "listaUsuarios?faces-redirect=true";

	}
	
	 public void onCellEdit(CellEditEvent event) {
	        Object oldValue = event.getOldValue();
	        Object newValue = event.getNewValue();
	         
	        if(newValue != null && !newValue.equals(oldValue)) {     	
	        	//UsuarioDAO.getInstance().atualiza(this.usuario);
	        	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Célula alterada", "Anterior: " + oldValue + ", novo:" + newValue);
	            FacesContext.getCurrentInstance().addMessage(null, msg);	            
	        }
	    }

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	
}
