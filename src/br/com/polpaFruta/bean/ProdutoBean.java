package br.com.polpaFruta.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.polpaFruta.dao.ProdutoDAO;
import br.com.polpaFruta.dao.SaborDAO;
import br.com.polpaFruta.dao.TpPolpaDAO;
import br.com.polpaFruta.dao.TpProdutoDAO;
import br.com.polpaFruta.domain.Produto;
import br.com.polpaFruta.domain.Sabor;
import br.com.polpaFruta.domain.TpPolpa;
import br.com.polpaFruta.domain.TpProduto;
import br.com.polpaFruta.util.JSFUtil;

@ManagedBean (name="MBProduto")
@ViewScoped
public class ProdutoBean {
	
	
	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltro;
	private Produto produto;
	private ArrayList<TpProduto> comboProduto;
	private ArrayList<Sabor> comboSabor;
	private ArrayList<TpPolpa> comboTpPolpa;
	
	
			
	public ArrayList<Produto> getItensFiltro() {
		return itensFiltro;
	}
	public void setItensFiltro(ArrayList<Produto> itensFiltro) {
		this.itensFiltro = itensFiltro;
	}
	public ArrayList<TpPolpa> getComboTpPolpa() {
		return comboTpPolpa;
	}
	public void setComboTpPolpa(ArrayList<TpPolpa> comboTpPolpa) {
		this.comboTpPolpa = comboTpPolpa;
	}
	public ArrayList<Sabor> getComboSabor() {
		return comboSabor;
	}
	public void setComboSabor(ArrayList<Sabor> comboSabor) {
		this.comboSabor = comboSabor;
	}
	public ArrayList<TpProduto> getComboProduto() {
		return comboProduto;
	}
	public void setComboProduto(ArrayList<TpProduto> comboProduto) {
		this.comboProduto = comboProduto;
	}
	public ArrayList<Produto> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
			
					
	
	@PostConstruct
	public void listaProduto() {
		
		ProdutoDAO pd = new ProdutoDAO();
		try {
			itens = pd.consultarProduto();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
	}
	
	public void preparaProduto() {
		
		produto = new Produto();
		TpProdutoDAO tpd = new TpProdutoDAO();
		SaborDAO sd = new SaborDAO();
		TpPolpaDAO tp = new TpPolpaDAO();
		try {
			comboProduto = tpd.consultarTpProduto();
			comboSabor = sd.consultarSabor();
			comboTpPolpa = tp.consultarTpPolpa();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void comboTpProduto() {
		
		TpProdutoDAO tpd = new TpProdutoDAO();
		try {
			comboProduto = tpd.consultarTpProduto();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void comboSabor() {
		
		SaborDAO sd = new SaborDAO();
		try {
			comboSabor = sd.consultarSabor();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void comboTpPolpa() {
		
		TpPolpaDAO tpd = new TpPolpaDAO();
		try {
			comboTpPolpa = tpd.consultarTpPolpa();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void cadastrarProduto() {
		
		ProdutoDAO pd = new ProdutoDAO();
		try {
			pd.cadastrarProduto(produto);
			listaProduto();
			JSFUtil.exibirMsgSucesso("Produto inserido com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.exibirMsgSucesso(e.getMessage());
		}
		
		
	}
	
	public void excluirProduto() {
		
		ProdutoDAO pd = new ProdutoDAO();
		try {
			pd.excluirProduto(produto);
			listaProduto();
			JSFUtil.exibirMsgSucesso("Produto excluido com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.exibirMsgErro(e.getMessage());
		}
		
	}
	
	public void alterarProduto() {
		
		ProdutoDAO pd = new ProdutoDAO();
		try {
			pd.atualizarProduto(produto);
			listaProduto();
			JSFUtil.exibirMsgSucesso("Produto alterado com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.exibirMsgErro(e.getMessage());
		}
		
		
	}

}

