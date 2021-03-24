package br.com.polpaFruta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.polpaFruta.domain.TpPolpa;
import br.com.polpaFruta.factory.ConexaoFactory;

public class TpPolpaDAO extends TpPolpa {

	@Override
	public ArrayList<TpPolpa> consultarTpPolpa() throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tppolpa");
		
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		ResultSet rs = pps.executeQuery();
		
		ArrayList<TpPolpa> lista = new ArrayList<>();
		
		
		
		while(rs.next()) {
			
			TpPolpa tp = new TpPolpa();
			tp.setId(rs.getInt("id"));
			tp.setDescricao(rs.getString("descricao"));
			
			lista.add(tp);
			
		}
		
		return lista;
	}
	
}
