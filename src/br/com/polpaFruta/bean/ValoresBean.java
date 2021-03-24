package br.com.polpaFruta.bean;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.polpaFruta.dao.ProdutoDAO;
import br.com.polpaFruta.dao.TpValorDAO;
import br.com.polpaFruta.dao.ValoresDAO;
import br.com.polpaFruta.domain.Produto;
import br.com.polpaFruta.domain.TpValor;
import br.com.polpaFruta.domain.Valores;
import br.com.polpaFruta.util.JSFUtil;

@ManagedBean (name="MBValores")
@ViewScoped
public class ValoresBean {

	private ArrayList<Valores> itens;
	private ArrayList<Valores> itensFiltro;
	private Valores valores;
	private ArrayList<Produto> comboProduto;
	private ArrayList<TpValor> comboTpValor;

	
				


	public ArrayList<TpValor> getComboTpValor() {
		return comboTpValor;
	}
	public void setComboTpValor(ArrayList<TpValor> comboTpValor) {
		this.comboTpValor = comboTpValor;
	}
	public ArrayList<Valores> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Valores> itens) {
		this.itens = itens;
	}
	public ArrayList<Valores> getItensFiltro() {
		return itensFiltro;
	}
	public void setItensFiltro(ArrayList<Valores> itensFiltro) {
		this.itensFiltro = itensFiltro;
	}
	public Valores getValores() {
		return valores;
	}
	public void setValores(Valores valores) {
		this.valores = valores;
	}
	public ArrayList<Produto> getComboProduto() {
		return comboProduto;
	}
	public void setComboProduto(ArrayList<Produto> comboProduto) {
		this.comboProduto = comboProduto;
	}
	
	
	public  void preparaValores() {
		
		valores = new Valores();
	
	}
	
	
	public void preparaCombos() {
		valores = new Valores();
		comboProduto();
		comboTpValor();
		
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
	
	public void comboTpValor() {
		
	    TpValorDAO tv = new TpValorDAO();
	    try {
			comboTpValor = tv.consultarTipoValor();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@PostConstruct
	public void listaValores() {
		
		ValoresDAO vd = new ValoresDAO();
		try {
			itens = vd.consultarValoresProdutos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void inserirValor() {
		
		ValoresDAO vd = new ValoresDAO();
		try {
			try {
				vd.cadastrarValores(valores);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listaValores();
			JSFUtil.exibirMsgSucesso("Registro inserido com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.exibirMsgErro(e.getMessage());
		}
		
		
	}
	
	
	public void excluirValor() {
		
		ValoresDAO vd = new ValoresDAO();
		try {
			vd.excluirValores(valores);
			listaValores();
			JSFUtil.exibirMsgSucesso("Registro excluído com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.exibirMsgErro(e.getMessage());
		}
		
		
	}
	
	
	
}

