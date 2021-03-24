package br.com.polpaFruta.bean;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.polpaFruta.dao.ProdutoDAO;
import br.com.polpaFruta.dao.VendaDAO;
import br.com.polpaFruta.domain.Produto;
import br.com.polpaFruta.domain.Venda;
import br.com.polpaFruta.util.JSFUtil;

@ManagedBean(name = "MBVendas")
@ViewScoped
public class VendasBean {

	private ArrayList<Venda> itens;
	private ArrayList<Venda> itensFiltro;
	private Venda venda;
	private ArrayList<Produto> produtoCombo;
	private Venda vendaSomaValor;

	public Venda getVendaSomaValor() {
		return vendaSomaValor;
	}

	public void setVendaSomaValor(Venda vendaSomaValor) {
		this.vendaSomaValor = vendaSomaValor;
	}

	public ArrayList<Venda> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Venda> itens) {
		this.itens = itens;
	}

	public ArrayList<Venda> getItensFiltro() {
		return itensFiltro;
	}

	public void setItensFiltro(ArrayList<Venda> itensFiltro) {
		this.itensFiltro = itensFiltro;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public ArrayList<Produto> getProdutoCombo() {
		return produtoCombo;
	}

	public void setProdutoCombo(ArrayList<Produto> produtoCombo) {
		this.produtoCombo = produtoCombo;
	}

	public void instaciarVenda() {

		venda = new Venda();
		comboProduto();

	}

	@PostConstruct
	public void preparaListaVenda() {

		VendaDAO vd = new VendaDAO();
		recuperaValorTotalVenda();
		itens = vd.consultarVendas();
	}

	public void comboProduto() {

		ProdutoDAO pd = new ProdutoDAO();
		try {
			produtoCombo = pd.listarProdutoCombo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void inserirVenda() {

		VendaDAO vd = new VendaDAO();
		try {
			vd.cadastrarVenda(venda, "Temporario");
			preparaListaVenda();
			JSFUtil.exibirMsgSucesso("Registro inserido com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.exibirMsgErro(e.getMessage());
		}

	}

	public void excluirVenda() {

		VendaDAO vd = new VendaDAO();
		try {
			vd.excluirVenda(venda, "Temporario");
			preparaListaVenda();
			JSFUtil.exibirMsgSucesso("Registro excluido com sucesso!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.exibirMsgErro(e.getMessage());
		}

	}

	public void inserirValores() {

		VendaDAO ed = new VendaDAO();
		try {
			ed.cadastrarValores(venda);
			preparaListaVenda();
			JSFUtil.exibirMsgSucesso("Valor cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void recuperaValorTotalVenda() {
		VendaDAO vd = new VendaDAO();
		try {
			vendaSomaValor = vd.somaTotalVenda();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void confirmarItensVenda() {

		VendaDAO ed = new VendaDAO();
		try {
			ed.confirmarVenda();
			ed.excluirVendasTemporarias();
			recuperaValorTotalVenda();
			JSFUtil.exibirMsgSucesso("Confirmação de lançamentos realizados com sucesso!");
			preparaListaVenda();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
