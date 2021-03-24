package br.com.polpaFruta.domain;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Entrada {

	private Integer idEntrada;
	private Produto produto = new Produto();
	private Timestamp dataHora;
	private String dataHoraString;
	private Integer quantidade;
	


	public String getDataHoraString() {
		return dataHoraString;
	}
	public void setDataHoraString(String dataHoraString) {
		this.dataHoraString = dataHoraString;
	}
	public Integer getIdEntrada() {
		return idEntrada;
	}
	public void setIdEntrada(Integer idEntrada) {
		this.idEntrada = idEntrada;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Timestamp getDataHora() {
		return dataHora;
	}
	public void setDataHora(Timestamp dataHora) {
		this.dataHora = dataHora;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public void cadastrarEntrada(Entrada e,String status) throws SQLException{
		
	}
	
	public void excluirEntrada(Entrada e,String status) throws SQLException{
		
	}
	
	public ArrayList<Entrada> consultarEntrada(String status) throws SQLException{
		
		ArrayList<Entrada> lista = null;
		
		return lista;
	}	
	
	
	
}
