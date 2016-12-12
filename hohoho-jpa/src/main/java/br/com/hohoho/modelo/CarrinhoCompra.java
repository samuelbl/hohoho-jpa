package br.com.hohoho.modelo;

import static javax.persistence.FetchType.EAGER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrinhocompra")
public class CarrinhoCompra extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(fetch = EAGER)
	@JoinColumn(name = "carrinho_id")
	private List <ItemComercial> itens;
	
	private BigDecimal total;
	
	@OneToOne(fetch = EAGER)
	private Usuario usuario;

	

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public CarrinhoCompra(BigDecimal total) {
		this.total = total;
		itens = new ArrayList<>();
	}
	
	public CarrinhoCompra() {
		this(new BigDecimal(0.0));
	}
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public List <ItemComercial> getItens() {
		return itens;
	}

	public void setItens(ItemComercial item) {
		this.itens.add(item);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
