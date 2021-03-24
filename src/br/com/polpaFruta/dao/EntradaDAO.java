package br.com.polpaFruta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.polpaFruta.domain.Entrada;
import br.com.polpaFruta.domain.Produto;
import br.com.polpaFruta.domain.Valores;
import br.com.polpaFruta.factory.ConexaoFactory;

public class EntradaDAO extends Entrada {

	@Override
	public void cadastrarEntrada(Entrada e, String status) throws SQLException {

		StringBuilder dml = new StringBuilder();
		if (status == "Permanente") {
			dml.append("insert into tbentrada (id_tbproduto, quantidade) values");
		} else {
			dml.append("insert into tbentradat (id_tbproduto, quantidade) values");
		}
		dml.append("(?,?)");
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(dml.toString());
		pps.setInt(1, e.getProduto().getIdProduto());
		pps.setInt(2, e.getQuantidade());
		pps.executeUpdate();
		pps.close();
		con.close();
	}

	public void confirmarEntrada() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("insert into tbentrada ");
		sql.append("(id_tbproduto, data_hora, quantidade) ");
		sql.append("select id_tbproduto, data_hora, quantidade from tbentradat");
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		pps.executeUpdate();
		con.close();
		pps.close();

	}

	public void excluirEntradasTemporarias() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("delete from tbentradat");
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		pps.executeUpdate();
		pps.close();
		con.close();

	}

	@Override
	public void excluirEntrada(Entrada e, String status) throws SQLException {

		StringBuilder dml = new StringBuilder();
		if (status == "Permanente") {
			dml.append("delete from tbentrada where id = ?");
		} else {
			dml.append("delete from tbentradat where id = ?");
		}
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(dml.toString());
		pps.setInt(1, e.getIdEntrada());
		pps.executeUpdate();
		pps.close();
		con.close();
	}

	@Override
	public ArrayList<Entrada> consultarEntrada(String status) throws SQLException {

		ArrayList<Entrada> lista = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.id,");
		sql.append("d.id_tpvalor,");
		sql.append("a.id_tbproduto,");
		sql.append("case when c.descricao is null then ");
		sql.append("b.descricao else b.descricao ||' - '|| c.descricao ");
		sql.append("end produto,");
		sql.append("a.data_hora,");
		sql.append("a.quantidade,");
		sql.append("(select first 1 tbvalor.valor ");
		sql.append("from tbvalor ");
		sql.append("where cast(a.data_hora as date) >= ");
		sql.append("(select max(data) from tbvalor where ");
		sql.append("data <= cast(a.data_hora as date)and d.id_tpvalor = 1)");
		sql.append("and tbvalor.id_tbproduto = a.id_tbproduto order by data desc)as valor ,");
		sql.append("a.quantidade * d.valor as vlr_total ");
		sql.append(" from tbentradat a ");
		sql.append("inner join tbproduto b ");
		sql.append("on a.id_tbproduto = b.id ");
		sql.append("left join tbsabor c ");
		sql.append("on c.id = b.id_tbsabor ");
		sql.append("left join tbvalor d ");
		sql.append("on (d.id_tbproduto = b.id ");
		sql.append("and d.id_tpvalor = 1 ");
		sql.append("and d.id = (select first 1 ");
		sql.append("tbvalor.id ");
		sql.append("from tbvalor ");
		sql.append("where cast(a.data_hora as date) >= ");
		sql.append("(select max(data) ");
		sql.append("from tbvalor where ");
		sql.append("data <= cast(a.data_hora as date)and d.id_tpvalor = 1) ");
		sql.append("and tbvalor.id_tbproduto = a.id_tbproduto and tbvalor.id_tpvalor = 1 ");
		sql.append("order by data desc))");

		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		ResultSet rs = pps.executeQuery();

		while (rs.next()) {

			Entrada e = new Entrada();
			Produto p = new Produto();
			p.setIdProduto(rs.getInt("id_tbproduto"));
			p.setDescricao(rs.getString("produto"));
			e.setIdEntrada(rs.getInt("id"));
			e.setProduto(p);
			e.setDataHora(rs.getTimestamp("data_hora"));
			e.setQuantidade(rs.getInt("quantidade"));
			Valores v = new Valores();
			v.setIdTpValor(rs.getInt("id_tpvalor"));
			v.setValor(rs.getFloat("valor"));
			v.setValorTotalProduto(rs.getFloat("vlr_total"));
			p.setValor(v);

			lista.add(e);

		}

		return lista;

	}

	public Entrada somaTotalEntrada() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select sum(a.quantidade * ");
		sql.append("(select first 1 tbvalor.valor from tbvalor ");
		sql.append("where cast(a.data_hora as date) >= (select max(data) ");
		sql.append("from tbvalor where ");
		sql.append("data <= cast(a.data_hora as date) ");
		sql.append("and d.id_tpvalor = 1) ");
		sql.append("and tbvalor.id_tbproduto = a.id_tbproduto ");
		sql.append("and tbvalor.id_tpvalor = 1 ");
		sql.append("order by data desc))as vlr_total ");
		sql.append("from tbentradat a ");
		sql.append("inner join tbproduto b ");
		sql.append("on a.id_tbproduto = b.id ");
		sql.append("left join tbsabor c ");
		sql.append("on c.id = b.id_tbsabor ");
		sql.append("left join tbvalor d ");
		sql.append("on (d.id_tbproduto = b.id ");
		sql.append("and d.id_tpvalor = 1 ");
		sql.append("and d.id = (select first 1 tbvalor.id ");
		sql.append("from tbvalor ");
		sql.append("where cast(a.data_hora as date) >= ");
		sql.append("(select max(data) from ");
		sql.append("tbvalor where ");
		sql.append("data <= cast(a.data_hora as date) ");
		sql.append("and d.id_tpvalor = 1) ");
		sql.append("and tbvalor.id_tbproduto = a.id_tbproduto ");
		sql.append("and tbvalor.id_tpvalor = 1 ");
		sql.append("order by data desc))");	

		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		ResultSet rs = pps.executeQuery();

		Entrada e = new Entrada();
		Valores v = new Valores();
		Produto p = new Produto();

		while (rs.next()) {

			v.setValorTotalProduto(rs.getFloat("vlr_total"));
			p.setValor(v);
			e.setProduto(p);

		}

		return e;
	}

	public void cadastrarValores(Entrada e) throws SQLException, ParseException {

		StringBuilder dml = new StringBuilder();
		dml.append("insert into tbvalor (id_tbproduto,id_tpvalor,data,valor)");
		dml.append("values");
		dml.append("(?,?,?,?)");

		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(dml.toString());
		pps.setInt(1, e.getProduto().getIdProduto());
		pps.setInt(2, 1);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyyy");
		Date dataValor = format.parse(e.getDataHoraString());
		java.sql.Timestamp dataSql = new java.sql.Timestamp(dataValor.getTime());
		e.setDataHora(dataSql);
		pps.setTimestamp(3, e.getDataHora());
		pps.setFloat(4, e.getProduto().getValor().getValor());
		pps.executeUpdate();
		pps.close();
		con.close();

	}

}
