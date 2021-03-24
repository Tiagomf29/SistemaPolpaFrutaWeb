package br.com.polpaFruta.domain;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class Valores extends TpValor  {

	
	private Integer idValor;
	private Produto produto = new Produto();
	private Date data;
	private String dataString;
	private float valor;
	private float valorTotalProduto;
	
	
		
	public float getValorTotalProduto() {
		return valorTotalProduto;
	}
	public void setValorTotalProduto(float valorTotalProduto) {
		this.valorTotalProduto = valorTotalProduto;
	}
	public String getDataString() {
		return dataString;
	}
	public void setDataString(String dataString) {
		this.dataString = dataString;
	}
	public Integer getIdValor() {
		return idValor;
	}
	public void setIdValor(Integer idValor) {
		this.idValor = idValor;
	}

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
	
	public void cadastrarValores(Valores val) throws SQLException , ParseException{
		
	}
	
	public void excluirValores(Valores val) throws SQLException {
		
	}
	
	public ArrayList<Valores> consultarValorProduto(Valores val)throws SQLException  {
		
		return null;
		
	}
	
	public ArrayList<Valores> consultarValorCompraProduto(Produto p)throws SQLException {		
		
		return null;		
		
	}	
	
	
	public ArrayList<Valores> consultarValoresProdutos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
