package br.com.polpaFruta.domain;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Venda {
	
	

	private Integer idVenda;
	private Produto produto = new Produto();
	private Timestamp datHora;
	private String dataHoraString;
	private Integer quantidade;
	private float vlrUnitario;
	private float perDeconto;
	private float vlrTotal;
	
	
	public String getDataHoraString() {
		return dataHoraString;
	}
	public void setDataHoraString(String dataHoraString) {
		this.dataHoraString = dataHoraString;
	}
	public float getVlrTotal() {
		return vlrTotal;
	}
	public void setVlrTotal(float vlrTotal) {
		this.vlrTotal = vlrTotal;
	}
	public Integer getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Timestamp getDatHora() {
		return datHora;
	}
	public void setDatHora(Timestamp datHora) {
		this.datHora = datHora;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public float getVlrUnitario() {
		return vlrUnitario;
	}
	public void setVlrUnitario(float vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}
	public float getPerDeconto() {
		return perDeconto;
	}
	public void setPerDeconto(float perDeconto) {
		this.perDeconto = perDeconto;
	}
	
	
	public void cadastrarVenda(Venda v) throws SQLException {
		
	}
	
	public void excluirVenda(Venda v,String status) throws SQLException{
		
	}
	
	public ArrayList<Venda> consultarProdutoMaisVendido() throws SQLException {
		
		ArrayList<Venda> lista = null;
		
		return lista;
		
	}
	
	public ArrayList<Venda> consultarProdutoMenosVendido() throws SQLException {
		
		ArrayList<Venda> lista = null;
		
		return lista;
		
	}
	
	public ArrayList<Venda> consultarValorVendaProduto() throws SQLException {
		ArrayList<Venda> lista = null;
		
		return lista;
	}
	
	public ArrayList<Venda> consultarMargemLucroProdutoMensal()throws SQLException  {
		ArrayList<Venda> lista = null;
		
		return lista;
	}
	
	public ArrayList<Venda> consultarMargemLucroProdutoAnual() throws SQLException {
		ArrayList<Venda> lista = null;
		
		return lista;
	}
	
	public ArrayList<Venda> consultarMargemLucroProdutoGeral() throws SQLException {
		ArrayList<Venda> lista = null;
		
		return lista;
	}
	
	public ArrayList<Venda> consultarVendas(){
		
		ArrayList<Venda>lista = null;
		
		return lista;
		
		
	}

	
}


