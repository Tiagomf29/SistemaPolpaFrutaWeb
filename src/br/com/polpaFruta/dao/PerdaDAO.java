package br.com.polpaFruta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.polpaFruta.domain.Perda;
import br.com.polpaFruta.domain.Produto;
import br.com.polpaFruta.factory.ConexaoFactory;

public class PerdaDAO extends Perda{

	
	
	 @Override
	public void cadastrarPerda(Perda p) throws SQLException {
	
		 StringBuilder dml = new StringBuilder();
		 dml.append("insert into tbperda (id_tbproduto,qtde)");
		 dml.append("values");
		 dml.append("(?,?)");
		 
		 Connection con = ConexaoFactory.conectar();
		 PreparedStatement pps = con.prepareStatement(dml.toString());
		 pps.setInt(1, p.getProduto().getIdProduto());
		 pps.setInt(2, p.getQtde());
		 pps.executeUpdate();
		 pps.close();
		 con.close();
		 
	}
	 
	 @Override
	public void excluirPerda(Perda p) throws SQLException {
	
		 StringBuilder dml = new StringBuilder();
		 dml.append("delete from tbperda where id = ?");
		 Connection con = ConexaoFactory.conectar();
		 PreparedStatement pps = con.prepareStatement(dml.toString());
		 pps.setInt(1, p.getIdPerda());
		 pps.executeUpdate();
		 pps.close();
		 con.close();
		 
	}
	 
	 @Override
	public ArrayList<Perda> consultarPerda() throws SQLException {
		 
		 StringBuilder sql = new StringBuilder();
		 sql.append("select tbperda.id as id_perda,");
		 sql.append("tbperda.id_tbproduto as id_tbproduto,");
		 sql.append("tbproduto.descricao||' - '||tbsabor.descricao as descricao,");
		 sql.append("tbperda.data_hora as dataHora, ");
		 sql.append("qtde as quantidade ");
		 sql.append("from tbperda inner join tbproduto on tbperda.id_tbproduto = tbproduto.id inner join tbsabor on tbsabor.id = tbproduto.id_tbsabor");
		 
		 Connection con = ConexaoFactory.conectar();
		 PreparedStatement pps = con.prepareStatement(sql.toString());
		 ResultSet rs = pps.executeQuery();
		 ArrayList<Perda> lista = new ArrayList<>();
		 
		 while(rs.next()) {
			 
			 Perda p = new Perda();
			 Produto pr = new Produto();
			 p.setQtde(rs.getInt("quantidade"));
			 p.setIdPerda(rs.getInt("id_perda"));
			 pr.setIdProduto(rs.getInt("id_tbproduto"));
			 pr.setDescricao(rs.getString("descricao"));
			 p.setProduto(pr);
			 p.setDataHoraPerda(rs.getTimestamp("dataHora"));
			 
			 lista.add(p);
			 
		 }
		 
		 return lista;
	}
	
}
