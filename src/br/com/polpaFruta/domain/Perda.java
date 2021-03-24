package br.com.polpaFruta.domain;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Perda {
	
	private Integer idPerda;
	private Produto produto = new Produto();
	private Timestamp dataHoraPerda;
	private Integer qtde;
	
			
	public Integer getQtde() {
		return qtde;
	}
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}
	public Integer getIdPerda() {
		return idPerda;
	}
	public void setIdPerda(Integer idPerda) {
		this.idPerda = idPerda;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Timestamp getDataHoraPerda() {
		return dataHoraPerda;
	}
	public void setDataHoraPerda(Timestamp dataHoraPerda) {
		this.dataHoraPerda = dataHoraPerda;
	}
	
	
	public void cadastrarPerda(Perda p) throws SQLException {
		
	}
	
	public void excluirPerda(Perda p) throws SQLException {
		
	}
	
	public ArrayList<Perda> consultarPerda() throws SQLException {
		
		
		ArrayList<Perda> lista = null;
		
		return lista;
		
	}
	
}
