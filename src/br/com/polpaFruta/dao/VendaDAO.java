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
import br.com.polpaFruta.domain.Venda;
import br.com.polpaFruta.factory.ConexaoFactory;

public class VendaDAO extends Venda {

	public void cadastrarVenda(Venda v, String status) throws SQLException {

		StringBuilder dml = new StringBuilder();

		if (status == "Permanente") {
			dml.append("insert into tbvenda (id_tbproduto,data_hora,quantidade,vlr_unitario,perc_desconto)");
		} else {
			dml.append("insert into tbvendat (id_tbproduto,data_hora,quantidade,perc_desconto)");
		}
		dml.append("values");
		
		if (status == "Permanente") {
			dml.append("(?,?,?,?,?)");
		}else {
		dml.append("(?,?,?,?)");
		}

		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(dml.toString());
		pps.setInt(1, v.getProduto().getIdProduto());
		pps.setTimestamp(2, v.getDatHora());
		pps.setInt(3, v.getQuantidade());
		if (status == "Permanente") {
			pps.setFloat(4, v.getVlrUnitario());
			pps.setFloat(5, v.getPerDeconto());
		} else {
			pps.setFloat(4, v.getPerDeconto());
		}

		pps.executeUpdate();
		pps.close();
		con.close();

	}

	@Override
	public void excluirVenda(Venda v, String status) throws SQLException {

		StringBuilder dml = new StringBuilder();

		if (status == "Permanente") {
			dml.append("delete from tbvenda where id = ?");
		} else {
			dml.append("delete from tbvendat where id = ?");
		}

		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(dml.toString());
		pps.setInt(1, v.getIdVenda());
		pps.executeUpdate();
		con.close();

	}

	@Override
	public ArrayList<Venda> consultarProdutoMaisVendido() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select  a.id_tbproduto,");
		sql.append("b.descricao,");
		sql.append("sum(a.quantidade)as qtde ");
		sql.append("from tbvenda a ");
		sql.append("inner join tbproduto b ");
		sql.append("on a.id_tbproduto = b.id ");
		sql.append("group by a.id_tbproduto, ");
		sql.append("b.descricao ");
		sql.append("having sum(quantidade) = ( ");
		sql.append("select max(qtde) from ( ");
		sql.append("select  id_tbproduto,");
		sql.append("sum(quantidade)as qtde ");
		sql.append("from tbvenda ");
		sql.append("group by id_tbproduto))");

		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		ResultSet rs = pps.executeQuery();
		ArrayList<Venda> lista = new ArrayList<>();

		while (rs.next()) {

			Venda v = new Venda();
			Produto p = new Produto();
			p.setIdProduto(rs.getInt("id_tbproduto"));
			p.setDescricao(rs.getString("descricao"));
			v.setProduto(p);
			v.setQuantidade(rs.getInt("qtde"));
			lista.add(v);

		}

		return lista;

	}

	@Override
	public ArrayList<Venda> consultarProdutoMenosVendido() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select  a.id_tbproduto,");
		sql.append("b.descricao,");
		sql.append("sum(a.quantidade)as qtde ");
		sql.append("from tbvenda a ");
		sql.append("inner join tbproduto b ");
		sql.append("on a.id_tbproduto = b.id ");
		sql.append("group by a.id_tbproduto, ");
		sql.append("b.descricao ");
		sql.append("having sum(quantidade) = ( ");
		sql.append("select min(qtde) from ( ");
		sql.append("select  id_tbproduto,");
		sql.append("sum(quantidade)as qtde ");
		sql.append("from tbvenda ");
		sql.append("group by id_tbproduto))");

		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		ResultSet rs = pps.executeQuery();
		ArrayList<Venda> lista = new ArrayList<>();

		while (rs.next()) {

			Venda v = new Venda();
			Produto p = new Produto();
			p.setIdProduto(rs.getInt("id_tbproduto"));
			p.setDescricao(rs.getString("descricao"));
			v.setProduto(p);
			v.setQuantidade(rs.getInt("qtde"));
			lista.add(v);

		}

		return lista;

	}

	@Override
	public ArrayList<Venda> consultarValorVendaProduto() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select a.data_hora,");
		sql.append("b.id,b.descricao,");
		sql.append("a.vlr_unitario as valor_venda ");
		sql.append("from tbvenda a ");
		sql.append("inner join tbproduto b ");
		sql.append("on a.id_tbproduto = b.id ");

		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		ResultSet rs = pps.executeQuery();
		ArrayList<Venda> lista = new ArrayList<>();

		while (rs.next()) {

			Venda v = new Venda();
			Produto p = new Produto();
			p.setIdProduto(rs.getInt("id"));
			p.setDescricao(rs.getString("descricao"));

			v.setDatHora(rs.getTimestamp("data_hora"));
			v.setProduto(p);
			v.setVlrUnitario(rs.getFloat("valor_venda"));
			lista.add(v);
		}

		return lista;

	}

	@Override
	public ArrayList<Venda> consultarVendas() {

		StringBuilder sql = new StringBuilder();
		sql.append("select a.id,");
		sql.append("b.id as id_produto,");
		sql.append("b.descricao ||' - '||c.descricao as produto,");
		sql.append("a.data_hora,");
		sql.append("a.quantidade,");
		sql.append("(select first 1 tbvalor.valor ");
		sql.append("from tbvalor ");
		sql.append("where cast(a.data_hora as date) >= ");
		sql.append("(select max(data) from tbvalor ");
		sql.append("where data <= ");
		sql.append("cast(a.data_hora as date) ");
		sql.append("and id_tpvalor = 2) ");
		sql.append("and id_tpvalor = 2 ");
		sql.append("and id_tbproduto = a.id_tbproduto ");
		sql.append("order by data desc ) vlr_unitario,");
		sql.append("a.perc_desconto,");
		sql.append("(a.quantidade * (select first 1 ");
		sql.append("tbvalor.valor ");
		sql.append("from tbvalor ");
		sql.append("where cast(a.data_hora as date) >= ");
		sql.append("(select max(data) from tbvalor ");
		sql.append("where data <= cast(a.data_hora as date) ");
		sql.append("and id_tpvalor = 2) ");
		sql.append("and id_tpvalor = 2 ");
		sql.append("and id_tbproduto = a.id_tbproduto ");
		sql.append("order by data desc )) - ");
		sql.append("((a.quantidade * (select first 1 ");
		sql.append("tbvalor.valor ");
		sql.append("from tbvalor ");
		sql.append("where cast(a.data_hora as date) >= ");
		sql.append("(select max(data) from ");
		sql.append("tbvalor ");
		sql.append("where data <= ");
		sql.append("cast(a.data_hora as date) ");
		sql.append("and id_tpvalor = 2)");
		sql.append("and id_tpvalor = 2 ");
		sql.append("and id_tbproduto = a.id_tbproduto ");
		sql.append("order by data desc ) ) * ");
		sql.append("a.perc_desconto)/100 as total ");
		sql.append("from tbvendat a ");
		sql.append("inner join tbproduto b on a.id_tbproduto = b.id ");
		sql.append("inner join tbsabor c on c.id = b.id_tbsabor ");
		sql.append("left join tbvalor d  on (d.id_tbproduto = b.id ");
		sql.append("and d.id_tpvalor = 2 and d.id = ");
		sql.append("(select first 1 tbvalor.id ");
		sql.append("from tbvalor ");
		sql.append("where cast(a.data_hora as date) >= ");
		sql.append("(select max(data) ");
		sql.append("from tbvalor where ");
		sql.append("data <= cast(a.data_hora as date) ");
		sql.append("and id_tpvalor = 2) ");
		sql.append("and id_tbproduto = a.id_tbproduto ");
		sql.append("order by data desc ))");
		sql.append("order by 1");

		Connection con = null;
		try {
			con = ConexaoFactory.conectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement pps = null;
		try {
			pps = con.prepareStatement(sql.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = pps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Venda> lista = new ArrayList<>();

		try {
			while (rs.next()) {

				Venda v = new Venda();
				Produto p = new Produto();
				p.setDescricao(rs.getString("produto"));
				p.setIdProduto(rs.getInt("id_produto"));
				v.setIdVenda(rs.getInt("id"));
				v.setProduto(p);
				v.setDatHora(rs.getTimestamp("data_hora"));
				v.setQuantidade(rs.getInt("quantidade"));
				v.setVlrUnitario(rs.getFloat("vlr_unitario"));
				v.setPerDeconto(rs.getFloat("perc_desconto"));
				v.setVlrTotal(rs.getFloat("total"));

				lista.add(v);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}

	public Venda vlrUnitario() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select valor from tbvalor where id_tpvalor = 2");

		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());

		ResultSet rs = pps.executeQuery();
		Venda v = new Venda();
		while (rs.next()) {

			v.setVlrUnitario(rs.getFloat("valor"));
		}

		return v;

	}

	public void cadastrarValores(Venda v) throws SQLException, ParseException {

		StringBuilder dml = new StringBuilder();
		dml.append("insert into tbvalor (id_tbproduto,id_tpvalor,data,valor)");
		dml.append("values");
		dml.append("(?,?,?,?)");

		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(dml.toString());
		pps.setInt(1, v.getProduto().getIdProduto());
		pps.setInt(2, 2);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyyy");
		Date dataValor = format.parse(v.getDataHoraString());
		java.sql.Timestamp dataSql = new java.sql.Timestamp(dataValor.getTime());
		v.setDatHora(dataSql);
		pps.setTimestamp(3, v.getDatHora());
		pps.setFloat(4, v.getVlrUnitario());
		pps.executeUpdate();
		pps.close();
		con.close();

	}
	
	public Venda somaTotalVenda() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("sum( ");
		sql.append("(a.quantidade * ");
		sql.append("(select first 1 tbvalor.valor from tbvalor where cast(a.data_hora as date) >= ");		
		sql.append("(select max(data) from tbvalor where data <= cast(a.data_hora as date)and id_tpvalor = 2) ");
		sql.append("and id_tpvalor = 2 and id_tbproduto = a.id_tbproduto order by data desc)) -  ");
		sql.append("(a.quantidade * ");
		sql.append("(select first 1 tbvalor.valor from tbvalor where cast(a.data_hora as date) >= ");
		sql.append("(select max(data) from tbvalor where data <= cast(a.data_hora as date)and id_tpvalor = 2) ");
		sql.append("and id_tpvalor = 2 and id_tbproduto = a.id_tbproduto order by data desc)) * a.perc_desconto / 100) as vlr_total ");
		sql.append("from tbvendat a ");
		sql.append("inner join tbproduto b on a.id_tbproduto = b.id ");
		sql.append("inner join tbsabor c on c.id = b.id_tbsabor ");
		sql.append("left join tbvalor d  on (d.id_tbproduto = b.id and d.id_tpvalor = 2 and d.id = ");
		sql.append("(select first 1 tbvalor.id from tbvalor where cast(a.data_hora as date) >= (select max(data) from tbvalor where ");
		sql.append("data <= cast(a.data_hora as date)and id_tpvalor = 2) and id_tbproduto = a.id_tbproduto order by data desc )) ");
		sql.append("order by 1");

		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		ResultSet rs = pps.executeQuery();

		Venda e = new Venda();

		while (rs.next()) {

			e.setVlrTotal(rs.getFloat("vlr_total"));						

		}

		return e;
	}	

	public void confirmarVenda() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("insert into tbvenda ");
		sql.append("(id_tbproduto, data_hora, quantidade,vlr_unitario,perc_desconto) ");
		sql.append("select b.id as id_produto,");
		sql.append("a.data_hora,");
		sql.append("a.quantidade,");
		sql.append("(select first 1 tbvalor.valor from tbvalor where cast(a.data_hora as date) >= (select max(data) from tbvalor where ");
		sql.append("data <= cast(a.data_hora as date)and id_tpvalor = 2) and id_tpvalor = 2 and id_tbproduto = a.id_tbproduto order by data desc )as vlr_unitario,");
		sql.append("a.perc_desconto ");
		sql.append("from tbvendat a ");
		sql.append("inner join tbproduto b on a.id_tbproduto = b.id ");
		sql.append("inner join tbsabor c on c.id = b.id_tbsabor ");
		sql.append("left join tbvalor d  on (d.id_tbproduto = b.id and d.id_tpvalor = 2 and d.id = ");
		sql.append("(select first 1 tbvalor.id from tbvalor where cast(a.data_hora as date) >= (select max(data) from tbvalor where ");
		sql.append("data <= cast(a.data_hora as date)and id_tpvalor = 2) and id_tbproduto = a.id_tbproduto order by data desc )) ");
		sql.append("order by 1");
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		pps.executeUpdate();
		con.close();
		pps.close();

	}

	
	public void excluirVendasTemporarias() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("delete from tbvendat");
		Connection con = ConexaoFactory.conectar();
		PreparedStatement pps = con.prepareStatement(sql.toString());
		pps.executeUpdate();
		pps.close();
		con.close();

	}
	
}
