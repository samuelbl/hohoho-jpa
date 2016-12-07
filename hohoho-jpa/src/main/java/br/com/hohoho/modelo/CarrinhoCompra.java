package br.com.hohoho.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "carrinhocompra")
public class CarrinhoCompra extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private ArrayList <ItemComercial> itens = new ArrayList<>();
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
