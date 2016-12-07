package br.com.hohoho.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.hohoho.dao.ItemComercialDAO;
import br.com.hohoho.dao.ProdutoDAO;
import br.com.hohoho.modelo.ItemComercial;
import br.com.hohoho.modelo.Produto;
import br.com.hohoho.modelo.Venda;

@ManagedBean
@ViewScoped
public class VendasBean {
	public List<Venda> getVendas(long seed) {
		List<Produto> produtos = ProdutoDAO.getInstance().listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();
		for (Produto produto : produtos) {
			Integer quantidade = 0;
			BigDecimal total = BigDecimal.ZERO;
			List <ItemComercial> listaItemComerial = ItemComercialDAO.getInstance().retornarListaIdProduto(String.valueOf(produto.getId()));
			for (ItemComercial itemComercial : listaItemComerial) {
				quantidade+=Integer.valueOf(itemComercial.getQuantidade());
				total = total.add(itemComercial.getTotal());
			}
			vendas.add(new Venda(produto,quantidade,total));
		}
		return vendas;
	}

	public BarChartModel getVendasModel() {
		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Quantidade");
		List<Venda> vendas = getVendas(1234);
		for (Venda venda : vendas) {
			vendaSerie.set(venda.getProduto().getNome(), venda.getQuantidade());
		}
		ChartSeries vendaSerie2015 = new ChartSeries();
		vendaSerie2015.setLabel("ValorTotal");
		vendas = getVendas(4321);
		for (Venda venda : vendas) {
			vendaSerie2015.set(venda.getProduto().getNome(),venda.getTotal());
			System.out.println(venda.getTotal());
		}
		model.addSeries(vendaSerie);
		model.addSeries(vendaSerie2015);
		return model;
	}
}
