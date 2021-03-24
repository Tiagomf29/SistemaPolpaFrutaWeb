package br.com.polpaFruta.domain;

import java.sql.SQLException;
import java.util.ArrayList;

public class TpProduto {

	private Integer id;
	private String descricao;
	
	
	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public ArrayList<TpProduto> consultarTpProduto() throws SQLException {
		
		
		ArrayList<TpProduto> lista = null;
		
		return lista;
	}
	
}
