package br.com.polpaFruta.domain;

import java.sql.SQLException;
import java.util.ArrayList;

public class Produto {
	
	private Integer idProduto;
	private String descricao;
	private float peso;
	private Integer estoqueMinimo;
	private TpProduto tipoProduto = new TpProduto();
	private TpPolpa tipoPolpa = new TpPolpa();
	private Sabor sabor = new Sabor();
	private Valores valor;
	
	
	
	public Valores getValor() {
		return valor;
	}
	public void setValor(Valores valor) {
		this.valor = valor;
	}
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}
	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}
	public TpProduto getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(TpProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	public TpPolpa getTipoPolpa() {
		return tipoPolpa;
	}
	public void setTipoPolpa(TpPolpa tipoPolpa) {
		this.tipoPolpa = tipoPolpa;
	}
	public Sabor getSabor() {
		return sabor;
	}
	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}
	
	
	public void cadastrarProduto(Produto p) throws SQLException{
		
	}
	
	public void atualizarProduto(Produto p) throws SQLException{
		
	}
	
	public void excluirProduto(Produto p) throws SQLException{
		
	}
	
	public ArrayList<Produto> consultarProduto() throws SQLException{
		
		ArrayList<Produto> lista= null;
		
		return lista;
		
	}
}
