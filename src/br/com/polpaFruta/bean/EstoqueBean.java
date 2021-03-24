package br.com.polpaFruta.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.polpaFruta.dao.ProdutoDAO;
import br.com.polpaFruta.domain.Estoque;

@ManagedBean (name="MBEstoque")
@ViewScoped
public class EstoqueBean {
	
	
	private ArrayList<Estoque> itens;
	private ArrayList<Estoque> itensFiltro;
	
	public ArrayList<Estoque> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Estoque> itens) {
		this.itens = itens;
	}
	public ArrayList<Estoque> getItensFiltro() {
		return itensFiltro;
	}
	public void setItensFiltro(ArrayList<Estoque> itensFiltro) {
		this.itensFiltro = itensFiltro;
	}
	
	@PostConstruct
	public void preparaConsultaEstoque() {
		
		ProdutoDAO pd = new ProdutoDAO();
		try {
			itens = pd.consultaEstoque();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
