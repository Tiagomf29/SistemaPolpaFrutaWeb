package br.com.polpaFruta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.polpaFruta.domain.Sabor;
import br.com.polpaFruta.factory.ConexaoFactory;

public class SaborDAO extends Sabor {

	@Override
	public void cadastrarSabor(Sabor s) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into tbsabor (descricao) values (?)");
		
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		pps.setString(1, s.getDescricao());
		pps.executeUpdate();
		pps.close();
		con.close();
	}
	
	@Override
	public void atualizarSabor(Sabor s) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update tbsabor set descricao = ? where id = ?");
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		pps.setString(1, s.getDescricao());
		pps.setInt(2, s.getId());
		pps.executeUpdate();
		pps.close();
		con.close();				
	}
	
	@Override
	public void excluirSabor(Sabor s) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from tbsabor where id = ?");
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		pps.setInt(1, s.getId());
		pps.executeUpdate();
		pps.close();
		con.close();
	}
	
	@Override
	public ArrayList<Sabor> consultarSabor() throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tbsabor");
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		ResultSet rs = pps.executeQuery();
		ArrayList<Sabor> lista = new ArrayList<>();
		
		while(rs.next()) {
			
			Sabor s = new Sabor();
			s.setId(rs.getInt("id"));
			s.setDescricao(rs.getString("descricao"));
			lista.add(s);
			
			
		}
		
		return lista;
	}
	
	
	
}
