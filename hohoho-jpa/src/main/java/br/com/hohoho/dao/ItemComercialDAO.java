package br.com.hohoho.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.hohoho.modelo.ItemComercial;

public class ItemComercialDAO extends DAO<ItemComercial> {

	public static synchronized ItemComercialDAO getInstance() {
		if (instancia == null) {
			instancia = new ItemComercialDAO();
		}
		return instancia;
	}

	private static ItemComercialDAO instancia;

	private ItemComercialDAO() {
		super(ItemComercial.class);
		geraDados();
	}

	@Override
	void geraDados() {
		// TODO Auto-generated method stub

	}

	@Override
	public ItemComercial adiciona(ItemComercial t) {
		BigDecimal total = (t.getProduto().getValor().multiply(new BigDecimal(t.getQuantidade())));
		t.setTotal(total);
		return super.adiciona(t);
	}

	public List<ItemComercial> retornarListaIdProduto(String idProduto) {
		List<ItemComercial> listaItemComercial = ItemComercialDAO.getInstance().listaTodos();
		List<ItemComercial> listaItemComercialRetorno = new ArrayList<>();
		for (ItemComercial itemComercial : listaItemComercial) {
			if (itemComercial.getProduto().getId().equals(Long.valueOf(idProduto))) {
				listaItemComercialRetorno.add(itemComercial);
			}			
		}
		return listaItemComercialRetorno;
	}
}
