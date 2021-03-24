package br.com.polpaFruta.bean;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.polpaFruta.dao.EntradaDAO;
import br.com.polpaFruta.dao.ProdutoDAO;
import br.com.polpaFruta.domain.Entrada;
import br.com.polpaFruta.domain.Produto;
import br.com.polpaFruta.util.JSFUtil;

@ManagedBean(name="MBEntrada")
@ViewScoped
public class EntradaBean {

	
	private ArrayList<Entrada> itens;
	private ArrayList<Entrada> itensFiltro;
	private Entrada entrada;
	private Entrada entradaSomaValor;
	private float entradaSomaValor2;
	private ArrayList<Produto> comboProduto;
	
	
	
	
	public float getEntradaSomaValor2() {
		return entradaSomaValor2;
	}
	public void setEntradaSomaValor2(float entradaSomaValor2) {
		this.entradaSomaValor2 = entradaSomaValor2;
	}
	public Entrada getEntradaSomaValor() {
		return entradaSomaValor;
	}
	public void setEntradaSomaValor(Entrada entradaSomaValor) {
		this.entradaSomaValor = entradaSomaValor;
	}



	public ArrayList<Entrada> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Entrada> itens) {
		this.itens = itens;
	}
	public ArrayList<Entrada> getItensFiltro() {
		return itensFiltro;
	}
	public void setItensFiltro(ArrayList<Entrada> itensFiltro) {
		this.itensFiltro = itensFiltro;
	}
	public Entrada getEntrada() {
		return entrada;
	}
	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}
	public ArrayList<Produto> getComboProduto() {
		return comboProduto;
	}
	public void setComboProduto(ArrayList<Produto> comboProduto) {
		this.comboProduto = comboProduto;
	}
	
	public void instaciaEntrada() {
		
		entrada = new Entrada();
		entradaSomaValor = new Entrada();
		listaComboProduto();
		
	}
	
	public void listaComboProduto() {
		
		ProdutoDAO pd = new ProdutoDAO();
		try {
			comboProduto = pd.listarProdutoCombo();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void consultaEntradaTemporario() {
		
		instaciaEntrada();
		EntradaDAO ed = new EntradaDAO();
		try {
			itens = ed.consultarEntrada("Temporario");
			recuperaValorTotalEntrada();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void comboProduto() {
		
		ProdutoDAO pd = new ProdutoDAO();
		try {
			comboProduto = pd.listarProdutoCombo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insereEntradaTemporario() {
		
		EntradaDAO ed = new EntradaDAO();
		try {
			ed.cadastrarEntrada(entrada,"Temporario");			
			consultaEntradaTemporario();
			recuperaValorTotalEntrada();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void excluirEntradaTemporario() {
		
		EntradaDAO ed = new EntradaDAO();
		try {
			ed.excluirEntrada(entrada, "Temporario");
			consultaEntradaTemporario();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
	public void confirmarItensEntrada() {
		
		EntradaDAO ed = new EntradaDAO();	
		try {
			ed.confirmarEntrada();
			ed.excluirEntradasTemporarias();
			recuperaValorTotalEntrada();
			JSFUtil.exibirMsgSucesso("Confirmação de lançamentos realizados com sucesso!");
			consultaEntradaTemporario();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void recuperaValorTotalEntrada() {
		
		
		EntradaDAO ed = new EntradaDAO();		
		try {
			entradaSomaValor = ed.somaTotalEntrada();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	
	
	public void inserirValores() {
		
		EntradaDAO ed = new EntradaDAO();
		try {
			ed.cadastrarValores(entrada);
			consultaEntradaTemporario();
			JSFUtil.exibirMsgSucesso("Valor cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		
	}
		
	
}
