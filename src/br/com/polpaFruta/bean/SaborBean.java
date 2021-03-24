package br.com.polpaFruta.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.polpaFruta.dao.ProdutoDAO;
import br.com.polpaFruta.dao.SaborDAO;
import br.com.polpaFruta.domain.Sabor;
import br.com.polpaFruta.util.JSFUtil;

@ManagedBean(name = "MBSabor")
@ViewScoped
public class SaborBean {

	private ArrayList<Sabor> itens;
	private ArrayList<Sabor> itensFiltro;
	private Sabor sabor;

	public ArrayList<Sabor> getItensFiltro() {
		return itensFiltro;
	}

	public void setItensFiltro(ArrayList<Sabor> itensFiltro) {
		this.itensFiltro = itensFiltro;
	}

	public ArrayList<Sabor> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Sabor> itens) {
		this.itens = itens;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	@PostConstruct
	public void listaSabor() {

		SaborDAO sd = new SaborDAO();
		try {
			itens = sd.consultarSabor();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void instaciarSabor() {

		sabor = new Sabor();

	}

	public void cadastrarSabor() {

		SaborDAO sd = new SaborDAO();
		try {
			sd.cadastrarSabor(sabor);
			listaSabor();
			JSFUtil.exibirMsgSucesso("Cadastro realizado com sucesso!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.exibirMsgErro(e.getMessage());

		}

	}

	public void alterarSabor() {

		SaborDAO sd = new SaborDAO();
		try {
			sd.atualizarSabor(sabor);
			listaSabor();
			JSFUtil.exibirMsgSucesso("Alteração realizada com sucesso!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.exibirMsgErro(e.getMessage());
		}

	}

	public void excluirSabor() {

		SaborDAO sd = new SaborDAO();
		ProdutoDAO pd = new ProdutoDAO();

		try {
			if (pd.ValidaExclusaoProdutoSabor(sabor) == false) {

				JSFUtil.exibirMsgErro("Registro de sabor não pode ser excluido, pois existem produtos cadastrados com o mesmo.");
			} else {
				try {
					sd.excluirSabor(sabor);
					listaSabor();
					JSFUtil.exibirMsgSucesso("Exclusão realizada com sucesso!!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JSFUtil.exibirMsgErro(e.getMessage());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
