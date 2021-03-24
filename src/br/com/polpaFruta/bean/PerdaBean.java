package br.com.polpaFruta.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.polpaFruta.dao.PerdaDAO;
import br.com.polpaFruta.dao.ProdutoDAO;
import br.com.polpaFruta.domain.Perda;
import br.com.polpaFruta.domain.Produto;
import br.com.polpaFruta.util.JSFUtil;

@ManagedBean(name="MBPerda")
@ViewScoped
public class PerdaBean {
	
	
	private ArrayList<Perda> itens;
	private ArrayList<Perda> itensFiltro;
	private Perda perda;	
	private ArrayList<Produto> produtoCombo;
	
	public ArrayList<Perda> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Perda> itens) {
		this.itens = itens;
	}
	public ArrayList<Perda> getItensFiltro() {
		return itensFiltro;
	}
	public void setItensFiltro(ArrayList<Perda> itensFiltro) {
		this.itensFiltro = itensFiltro;
	}
	public Perda getPerda() {
		return perda;
	}
	public void setPerda(Perda perda) {
		this.perda = perda;
	}
	public ArrayList<Produto> getProdutoCombo() {
		return produtoCombo;
	}
	public void setProdutoCombo(ArrayList<Produto> produtoCombo) {
		this.produtoCombo = produtoCombo;
	}
	
	
	public void preparaPerda() {
		
		perda = new Perda();
		comboProduto();
	}
	
	public void excluirPerda() {
		
		
		PerdaDAO pd = new PerdaDAO();
		try {
			pd.excluirPerda(perda);
			listaPerdas();
			JSFUtil.exibirMsgSucesso("Perda excluída com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.exibirMsgSucesso(e.getMessage());
		}
		
		
	}
	
	@PostConstruct
	public void listaPerdas() {
		
		PerdaDAO pd = new PerdaDAO();
		try {
			itens = pd.consultarPerda();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	
	public void cadastrarPerda() {
		
		PerdaDAO pd = new PerdaDAO();
		try {
			pd.cadastrarPerda(perda);
			listaPerdas();
			JSFUtil.exibirMsgSucesso("Perda do produto realizada com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.exibirMsgErro(e.getMessage());
		}
		
		
	}
	

}
