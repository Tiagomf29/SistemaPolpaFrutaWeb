package br.com.polpaFruta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.polpaFruta.domain.Produto;
import br.com.polpaFruta.domain.Valores;
import br.com.polpaFruta.factory.ConexaoFactory;

public class ValoresDAO extends Valores{

	@Override
	public void cadastrarValores(Valores val) throws SQLException, ParseException {
		
		StringBuilder dml = new StringBuilder();
		dml.append("insert into tbvalor (id_tbproduto,id_tpvalor,data,valor)");
		dml.append("values");
		dml.append("(?,?,?,?)");
		
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(dml.toString());
		pps.setInt(1, val.getProduto().getIdProduto());
		pps.setInt(2, val.getIdTpValor());
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyyy");
		Date dataValor = format.parse(val.getDataString());
		java.sql.Date dataSql = new java.sql.Date(dataValor.getTime());
		val.setData(dataSql);		
		pps.setDate(3, val.getData());
		pps.setFloat(4, val.getValor());		
		pps.executeUpdate();
		pps.close();
		con.close();
		
	}
	
	
	@Override
	public void excluirValores(Valores val) throws SQLException {
	
		StringBuilder dml = new StringBuilder();
		dml.append("delete from tbvalor where id = ?");
		
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(dml.toString());
		pps.setInt(1, val.getIdValor());
		pps.executeUpdate();
		pps.close();
		con.close();
		
	}
	
	@Override
	public ArrayList<Valores> consultarValorProduto(Valores val) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.id as id,");
		sql.append("a.id_tbproduto as id_tbproduto,");
		sql.append("b.descricao produto,");
		sql.append("a.id_tpvalor id_tpvalor,");
		sql.append("c.descricao tipo_Valor,");
		sql.append("a.data as data,");
		sql.append("a.valor valor ");
		sql.append("from tbvalor a ");
		sql.append("inner join tbproduto b ");
		sql.append("on a.id_tbproduto = b.id ");
		sql.append("inner join tpvalor c on c.id = a.id_tpvalor ");
		sql.append("where a.id_tbproduto = ?");
		
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		pps.setInt(1, val.getProduto().getIdProduto());
		ResultSet rs = pps.executeQuery();
		ArrayList<Valores> lista = new ArrayList<>();
		
		while(rs.next()) {
			
			Valores v = new Valores();
			v.setIdValor(rs.getInt("id"));
			Produto p = new Produto();
			p.setIdProduto(rs.getInt("id_tbproduto"));
			p.setDescricao(rs.getString("produto"));			
			v.setProduto(p);
			v.setIdTpValor(rs.getInt("id_tpvalor"));
			v.setDescricaoTpValor(rs.getString("tipo_Valor"));
			v.setData(rs.getDate("data"));
			v.setValor(rs.getFloat("valor"));
			
			
			
			lista.add(v);
			
	
			
		}
		
		pps.close();
		con.close();
		return lista;		
		
	}
	
	
	@Override
	public ArrayList<Valores> consultarValoresProdutos() throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.id as id,");
		sql.append("a.id_tbproduto as id_tbproduto, ");
		sql.append("tbproduto.descricao||' - '|| tbsabor.descricao as produto, ");
		sql.append("a.id_tpvalor id_tpvalor, ");
		sql.append("c.descricao tipo_Valor, ");
		sql.append("a.data as data, ");
		sql.append("a.valor valor ");
		sql.append("from tbvalor a ");
		sql.append("inner join tbproduto ");
		sql.append("on a.id_tbproduto = tbproduto.id ");
		sql.append("inner join tpvalor c on c.id = a.id_tpvalor ");
		sql.append("inner join tbsabor on tbsabor.id = tbproduto.id_tbsabor ");

		
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		ResultSet rs = pps.executeQuery();
		ArrayList<Valores> lista = new ArrayList<>();
		
		while(rs.next()) {
			
			Valores v = new Valores();
			v.setIdValor(rs.getInt("id"));
			Produto p = new Produto();
			p.setIdProduto(rs.getInt("id_tbproduto"));
			p.setDescricao(rs.getString("produto"));
			v.setProduto(p);			
			v.setIdTpValor(rs.getInt("id_tpvalor"));
			v.setDescricaoTpValor(rs.getString("tipo_Valor"));						
			v.setData(rs.getDate("data"));			
			v.setValor(rs.getFloat("valor"));
									
			lista.add(v);						
		}
		
		pps.close();
		con.close();
		return lista;		
		
	}	
	
	@Override
	public ArrayList<Valores> consultarValorCompraProduto(Produto p) throws SQLException {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select data,valor from tbvalor where id_tbproduto = ? AND id_tpvalor = 1");
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sb.toString());
		pps.setInt(1, p.getIdProduto());
		ResultSet rs = pps.executeQuery();
		ArrayList<Valores> lista = new ArrayList<>();
		
		while(rs.next()) {
			
			Valores v = new Valores();
			v.setData(rs.getDate("data"));
			v.setValor(rs.getFloat("valor"));
			
			lista.add(v);
			
		}
		
		return lista;
		
	}
	
}
