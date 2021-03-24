package br.com.polpaFruta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.polpaFruta.domain.TpProduto;
import br.com.polpaFruta.factory.ConexaoFactory;

public class TpProdutoDAO extends TpProduto {

	@Override
	public ArrayList<TpProduto> consultarTpProduto() throws SQLException {
	
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tpproduto");
		
		Connection con =  ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		ResultSet rs = pps.executeQuery();
		ArrayList<TpProduto> lista = new ArrayList<>();
		
		while(rs.next()) {
			
			TpProduto tp = new TpProduto();
			tp.setId(rs.getInt("id"));
			tp.setDescricao(rs.getString("descricao"));
			lista.add(tp);
		}
		
		return lista;
	}
	
}
