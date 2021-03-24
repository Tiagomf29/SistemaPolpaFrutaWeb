package br.com.polpaFruta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.polpaFruta.domain.TpValor;
import br.com.polpaFruta.factory.ConexaoFactory;

public class TpValorDAO extends TpValor{

	
	@Override
	public ArrayList<TpValor> consultarTipoValor() throws SQLException {
	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tpvalor");
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		ResultSet rs = pps.executeQuery();
		ArrayList<TpValor> lista = new ArrayList<>();
		
		while(rs.next()) {
			
			TpValor tv = new TpValor();
			tv.setIdTpValor(rs.getInt("id"));
			tv.setDescricaoTpValor(rs.getString("descricao"));
			lista.add(tv);
		}
		
		return lista;
	}
	
}
