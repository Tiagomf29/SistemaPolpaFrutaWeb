package br.com.polpaFruta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.polpaFruta.domain.Estoque;
import br.com.polpaFruta.domain.Produto;
import br.com.polpaFruta.domain.Sabor;
import br.com.polpaFruta.domain.TpPolpa;
import br.com.polpaFruta.domain.TpProduto;
import br.com.polpaFruta.factory.ConexaoFactory;

public class ProdutoDAO extends Produto{
	
		@Override
		public void cadastrarProduto(Produto p) throws SQLException {
			
			StringBuilder dml = new StringBuilder();
			dml.append("insert into tbproduto ");
			dml.append("(id_tpproduto, descricao, peso, id_tbsabor, id_tppolpa, estoque_minimo)");
			dml.append("values");
			dml.append("(?,?,?,?,?,?)");
			
			StringBuilder dml2 = new StringBuilder();
			dml2.append("insert into tbproduto ");
			dml2.append("(id_tpproduto, descricao, peso, estoque_minimo)");
			dml2.append("values");
			dml2.append("(?,?,?,?)");			
			
			Connection con = ConexaoFactory.conectar();
			
			if(p.getTipoProduto().getId()==1) {
				PreparedStatement pps = con.prepareStatement(dml.toString());
				pps.setInt(1, p.getTipoProduto().getId());
				pps.setString(2, p.getDescricao());
				pps.setFloat(3, p.getPeso());
				pps.setInt(4, p.getSabor().getId());
				pps.setInt(5, p.getTipoPolpa().getId());
				pps.setInt(6, p.getEstoqueMinimo());			
				pps.executeUpdate();
				pps.close();
			}else {
				
				PreparedStatement pps = con.prepareStatement(dml2.toString());
				pps.setInt(1, p.getTipoProduto().getId());
				pps.setString(2, p.getDescricao());
				pps.setFloat(3, p.getPeso());
				pps.setInt(4, p.getEstoqueMinimo());			
				pps.executeUpdate();
				pps.close();
				
			}
									
			con.close();
			
		}
		
		@Override
		public void atualizarProduto(Produto p) throws SQLException {
			
			StringBuilder dml = new StringBuilder();
			dml.append("update tbproduto set id_tpproduto = ?,");
			dml.append("descricao = ?,");
			dml.append("peso = ?,");
			dml.append("id_tbsabor = ?,");
			dml.append("id_tppolpa = ?,");
			dml.append("estoque_minimo = ? ");
			dml.append("where id = ?");
			Connection con = ConexaoFactory.conectar();
			PreparedStatement pps = con.prepareStatement(dml.toString());
			pps.setInt(1, p.getTipoProduto().getId());
			pps.setString(2, p.getDescricao());
			pps.setFloat(3, p.getPeso());
			pps.setInt(4, p.getSabor().getId());
			pps.setInt(5, p.getTipoPolpa().getId());
			pps.setInt(6, p.getEstoqueMinimo());
			pps.setInt(7, p.getIdProduto());
			pps.executeUpdate();
			pps.close();
			con.close();

		}
		
		@Override
		public void excluirProduto(Produto p) throws SQLException {
			
			StringBuilder dml = new StringBuilder();
			dml.append("delete from tbproduto where id = ?");
			Connection con = ConexaoFactory.conectar();
			PreparedStatement pps = con.prepareStatement(dml.toString());
			pps.setInt(1, p.getIdProduto());
			pps.executeUpdate();
			pps.close();
			con.close();
		}
		
		@Override
		public ArrayList<Produto> consultarProduto() throws SQLException  {
			
			StringBuilder sql = new StringBuilder();
			sql.append("select a.id,");//
			sql.append("a.descricao as descricao_produto,");
			sql.append("a.id_tpproduto as id_tpproduto,"); //
			
			sql.append("b.descricao as desc_tp_produto,"); //
			sql.append("a.peso as peso,"); //
			sql.append("a.id_tbsabor as id_tbsabor,"); //
			sql.append("c.descricao as sabor,");
			sql.append("a.id_tppolpa as id_tppolpa,"); //
			sql.append("d.descricao as tipo_Polpa,"); //
			sql.append("a.estoque_minimo as estoque_minimo ");
			sql.append("from tbproduto a inner join tpproduto b on a.id_tpproduto = b.id ");
			sql.append("left join tbsabor c on c.id = a.id_tbsabor ");
			sql.append("left join tppolpa d on d.id = a.id_tppolpa");
			
			Connection con = ConexaoFactory.conectar();
			PreparedStatement pps = con.prepareStatement(sql.toString());
			ResultSet rs = pps.executeQuery();
			
			ArrayList<Produto> lista = new ArrayList<>();
			
			while(rs.next()) {
				
				Produto p = new Produto();
				
				TpProduto tp = new TpProduto();
				tp.setId(rs.getInt("id_tpproduto"));
				tp.setDescricao(rs.getString("desc_tp_produto"));
				
				SaborDAO sb = new SaborDAO();
				sb.setId(rs.getInt("id_tbsabor"));
				sb.setDescricao(rs.getString("sabor"));
				
				TpPolpa tpp = new TpPolpa();				
				tpp.setId(rs.getInt("id_tppolpa"));
				tpp.setDescricao(rs.getString("tipo_Polpa"));
				
				
				p.setIdProduto(rs.getInt("id"));
				p.setDescricao(rs.getString("descricao"));
				p.setTipoProduto(tp);			
				p.setPeso(rs.getFloat("peso"));
				p.setSabor(sb);
				p.setTipoPolpa(tpp);
				p.setEstoqueMinimo(rs.getInt("estoque_minimo"));
				
				lista.add(p);
				
			}

			return lista;
		}
		
		
public ArrayList<Produto> listarProdutoCombo() throws SQLException  {
			
			StringBuilder sql = new StringBuilder();
			sql.append("select tbproduto.id,");//
			sql.append("tbproduto.descricao ||' - '||tbsabor.descricao as produto,");
			sql.append("tbproduto.id_tpproduto as id_tpproduto,"); //
			
			sql.append("b.descricao as desc_tp_produto,"); //
			sql.append("tbproduto.peso as peso,"); //
			sql.append("tbproduto.id_tbsabor as id_tbsabor,"); //
			sql.append("tbsabor.descricao as sabor,");
			sql.append("tbproduto.id_tppolpa as id_tppolpa,"); //
			sql.append("d.descricao as tipo_Polpa,"); //
			sql.append("tbproduto.estoque_minimo as estoque_minimo ");
			sql.append("from tbproduto inner join tpproduto b on tbproduto.id_tpproduto = b.id ");
			sql.append("left join tbsabor  on tbsabor.id = tbproduto.id_tbsabor ");
			sql.append("left join tppolpa d on d.id = tbproduto.id_tppolpa ");	
			
			sql.append("union all ");
			
			sql.append("select tbproduto.id,");
			sql.append("tbproduto.descricao as produto,");
			sql.append("tbproduto.id_tpproduto as id_tpproduto,");
			sql.append("b.descricao as desc_tp_produto,");
			sql.append("tbproduto.peso as peso,");
			sql.append("tbproduto.id_tbsabor as id_tbsabor,");
			sql.append("tbproduto.id_tbsabor as sabor,");
			sql.append("tbproduto.id_tbsabor as id_tppolpa,");
			sql.append("tbproduto.id_tbsabor as tipo_Polpa,");
			sql.append("tbproduto.estoque_minimo as estoque_minimo ");
			sql.append("from tbproduto inner join tpproduto b on tbproduto.id_tpproduto = b.id ");
			sql.append("where (id_tbsabor is null or id_tppolpa is null)");
			
			Connection con = ConexaoFactory.conectar();
			PreparedStatement pps = con.prepareStatement(sql.toString());
			ResultSet rs = pps.executeQuery();
			
			ArrayList<Produto> lista = new ArrayList<>();
			
			while(rs.next()) {
				
				Produto p = new Produto();
				
				TpProduto tp = new TpProduto();
				tp.setId(rs.getInt("id_tpproduto"));
				tp.setDescricao(rs.getString("desc_tp_produto"));								
				p.setIdProduto(rs.getInt("id"));
				p.setDescricao(rs.getString("produto"));
				p.setTipoProduto(tp);			
				p.setPeso(rs.getFloat("peso"));
				p.setEstoqueMinimo(rs.getInt("estoque_minimo"));
			
				if(p.getDescricao()!=null) {
				lista.add(p);
				}
				
			}

			
			return lista;
		}		
	
		public Boolean ValidaExclusaoProdutoSabor(Sabor s) throws SQLException {
			
			boolean result = false;
			StringBuilder sql = new StringBuilder();
			sql.append("select * from tbproduto where id_tbsabor = ?");
			Connection con = ConexaoFactory.conectar();
			PreparedStatement pps = con.prepareStatement(sql.toString());
			pps.setInt(1, s.getId());
			ResultSet rs = pps.executeQuery();
			if(rs.next()) {
				result = false;
			}else {
				result = true;
			}
			
			return result;
		}
		
		
		public ArrayList<Estoque>consultaEstoque() throws SQLException{
			
			
			StringBuilder sql = new StringBuilder();
			sql.append("select b.id as codigo, b.descricao ||' - '||c.descricao as produto,a.qtde ");
			sql.append("from estoque a inner join tbproduto b on a.id_tbproduto = b.id ");
			sql.append("inner join tbsabor c on b.id_tbsabor = c.id");
			
			Connection con = ConexaoFactory.conectar();
			PreparedStatement pps = con.prepareStatement(sql.toString());
			ResultSet rs = pps.executeQuery();
			ArrayList<Estoque> lista = new ArrayList<>();
			
			while(rs.next()) {
				
				Estoque est = new Estoque();
				est.setCodigo(rs.getInt("codigo"));
				est.setDescricaoProduto(rs.getString("produto"));
				est.setQuantidade(rs.getInt("qtde"));
				lista.add(est);
				
			}
			
			
			
			return lista;
		}
}
