package br.com.polpaFruta.domain;

import java.sql.SQLException;
import java.util.ArrayList;

public class TpValor {
	
	private Integer idTpValor;
	private String descricaoTpValor;
	
	public Integer getIdTpValor() {
		return idTpValor;
	}
	public void setIdTpValor(Integer idTpValor) {
		this.idTpValor = idTpValor;
	}
	
	public String getDescricaoTpValor() {
		return descricaoTpValor;
	}
	
	public void setDescricaoTpValor(String descricaoTpValor) {
		this.descricaoTpValor = descricaoTpValor;
	}
	
	public void cadatrarTipoValor() {
		
	}
	
	public ArrayList<TpValor> consultarTipoValor()throws SQLException {
		return null;
	}

}
