package br.com.polpaFruta.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.polpaFruta.dao.EntradaDAO;
import br.com.polpaFruta.dao.PerdaDAO;
import br.com.polpaFruta.dao.ProdutoDAO;
import br.com.polpaFruta.dao.SaborDAO;
import br.com.polpaFruta.dao.TpPolpaDAO;
import br.com.polpaFruta.dao.TpProdutoDAO;
import br.com.polpaFruta.dao.ValoresDAO;
import br.com.polpaFruta.dao.VendaDAO;
import br.com.polpaFruta.domain.Entrada;
import br.com.polpaFruta.domain.Estoque;
import br.com.polpaFruta.domain.Perda;
import br.com.polpaFruta.domain.Produto;
import br.com.polpaFruta.domain.Sabor;
import br.com.polpaFruta.domain.TpPolpa;
import br.com.polpaFruta.domain.TpProduto;
import br.com.polpaFruta.domain.Valores;
import br.com.polpaFruta.domain.Venda;
import br.com.polpaFruta.factory.ConexaoFactory;

public class Testes {

	@Test
	@Ignore
	public void conectar() {
		
		try {
			ConexaoFactory.conectar();
			System.out.println("Conexão ok!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Conexão não ok!");
		}
	}
	
	@Test
	@Ignore
	public void inserirSabor() throws SQLException {
		
		SaborDAO sd = new SaborDAO();
		Sabor s = new Sabor();
		s.setDescricao("Limão");
		sd.cadastrarSabor(s);
		
	}
	
	
	@Test
	@Ignore
	public void ataulizarSabor() throws SQLException {
		
		SaborDAO sd = new SaborDAO();
		Sabor s = new Sabor();
		s.setDescricao("MARACUJA 2222");
		s.setId(1);
		sd.atualizarSabor(s);
		
	}
	
	@Test
	@Ignore
	public void consultarSabor() throws SQLException {
		
		SaborDAO sd = new SaborDAO();		
		ArrayList<Sabor> lista = sd.consultarSabor();
		
		for(Sabor s : lista) {
			
			System.out.println(s.getId()+" - "+s.getDescricao());
			
		}
		
		
	}
	
	@Test
	@Ignore
	public void consultaTipoProduto() throws SQLException {
		
		TpProdutoDAO tpd = new TpProdutoDAO();
		ArrayList<TpProduto> lista = tpd.consultarTpProduto();
		
		for(TpProduto tpp : lista) {
			
			System.out.println(tpp.getId() + "-" + tpp.getDescricao());
			
		}
		
		
	}
	
	@Test
	@Ignore
	public void inserirProduto() throws SQLException {
		
		ProdutoDAO pd = new ProdutoDAO();
		Produto p = new Produto();
		TpProduto tpp = new TpProduto();
		tpp.setId(2);
		p.setTipoProduto(tpp);
		p.setDescricao("TESTE_TIAGO____");
		p.setPeso(1.6F);		
		p.setEstoqueMinimo(1001);
		
		pd.cadastrarProduto(p);
		
		
	}
	
	@Test
	@Ignore
	public void atualizarProduto() throws SQLException {
		
		ProdutoDAO pd = new ProdutoDAO();
		
		TpPolpa polpa = new TpPolpa();
		TpProduto tipoProduto = new TpProduto();
		Sabor s = new Sabor();
		Produto p = new Produto();
		
		tipoProduto.setId(1);
		polpa.setId(1);
		s.setId(1);
		p.setIdProduto(3);
		
		p.setTipoProduto(tipoProduto);
		p.setDescricao("VALORATUALIZADO");
		p.setPeso(5.6F);
		p.setTipoPolpa(polpa);
		p.setSabor(s);
		p.setEstoqueMinimo(500);
		
		pd.atualizarProduto(p);
										
		
	}
	
	@Test
	@Ignore
	public void excluirProduto() throws SQLException {
		
		ProdutoDAO pd = new ProdutoDAO();
		pd.setIdProduto(3);
		pd.excluirProduto(pd);
		
	}
	
	@Test
	@Ignore
	public void consultaProduto() throws SQLException {
		
		ProdutoDAO pd = new ProdutoDAO();
		ArrayList<Produto> lista = pd.consultarProduto();
		
		for(Produto p : lista) {
			
			System.out.println(
					
					"ID produto: " + p.getIdProduto() + "\n" 					
					+"Descrição produto:" + p.getDescricao() + "\n\n"
					+ "Peso: "+ p.getPeso() + "\n\n"
					+ "Id Sabor: "+p.getSabor().getId() +"\n"
					+ "Descrição sabor:"+p.getSabor().getDescricao() +"\n\n"
					+ "Id Tipo produto:" + p.getTipoProduto().getId() +"\n"
					+ "Descrição produto:"+p.getTipoProduto().getDescricao() + "\n\n"
					+ "Id Sabor: "+p.getSabor().getId() + "\n"
					+ "Descrição sabor: "+p.getSabor().getDescricao() + "\n\n"
					+ "Id Polpa: " + p.getTipoPolpa().getId() + "\n"
					+ "Descrição Polpa: " + p.getTipoPolpa().getDescricao() + "\n\n"
					+ "Estoque mínimo: "+p.getEstoqueMinimo() );
			
		}
	}
	
	
	@Test
	@Ignore
	public void consultarTpPolpa() throws SQLException {
		
		TpPolpaDAO tpp = new TpPolpaDAO();
		ArrayList<TpPolpa> lista = tpp.consultarTpPolpa();
		
		for(TpPolpa t : lista) {
			
			System.out.println(t.getId() + "-" + t.getDescricao());
			
		}
		
	}
	
	
	@Test
	@Ignore
	public void insereValorProduto() throws SQLException, ParseException {
		
		ValoresDAO vd = new ValoresDAO();
		Produto p = new Produto();
		p.setIdProduto(21);
		vd.setProduto(p);
		vd.setIdTpValor(2);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date data = new java.sql.Date(format.parse("05/01/2018").getTime());
		vd.setData(data);
		vd.setValor(50f);
		
		vd.cadastrarValores(vd);
		
	}
	
	@Test
	@Ignore
	public void excluirValores() throws SQLException {
		
		ValoresDAO vd = new ValoresDAO();
		Valores v= new Valores();
		v.setIdValor(2);
		vd.excluirValores(v);
		
	}
	
	
	@Test
	@Ignore
	public void consultarProduto() throws SQLException {
		
		ValoresDAO vd = new ValoresDAO();
		Produto p = new Produto();
		p.setIdProduto(2);
		vd.setProduto(p);
		
		ArrayList<Valores> lista = vd.consultarValorProduto(vd);
		
		for(Valores v : lista) {
			
			System.out.println(v.getProduto().getDescricao() + " - "+ v.getValor());
			
		}
						
	}
	
	@Test
	@Ignore
	public void consultarComboProduto() throws SQLException {
		
		ProdutoDAO vd = new ProdutoDAO();

		
		ArrayList<Produto> lista = vd.listarProdutoCombo();
		
		for(Produto v : lista) {
			
			System.out.println(v.getDescricao());
			
		}
						
	}	
	
	@Test
	@Ignore
	public void cadatrarPerda() throws ParseException, SQLException {
		
		PerdaDAO pd = new PerdaDAO();
		Produto p = new Produto();
		p.setIdProduto(26);
		pd.setProduto(p);
		
		
		pd.cadastrarPerda(pd);
		
		
		System.out.println(pd.getDataHoraPerda());
	
		
			
		
		
	}
	
	@Test
	@Ignore
	public void excluirPerda() throws SQLException {
		
		PerdaDAO pd = new PerdaDAO();
		Produto p = new Produto();
		p.setIdProduto(2);
		pd.setProduto(p);
		pd.excluirPerda(pd);
		
	}
	
	@Test	
	@Ignore
	public void consultarPerda() throws SQLException {
		
		PerdaDAO pd = new PerdaDAO();
		ArrayList<Perda> lista = pd.consultarPerda();
		
		for(Perda p : lista) {
			
			System.out.println(p.getProduto().getIdProduto() + "-" + p.getProduto().getDescricao() + " - " + p.getDataHoraPerda());
			
		}
		
	}
	
	@Test
	@Ignore
	public void cadastrarEntrada() throws ParseException, SQLException {
		
		EntradaDAO ed = new EntradaDAO();
		Produto p = new Produto();
		p.setIdProduto(2);
		ed.setProduto(p);
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Long datHora = formato.parse("06/01/2019 17:20:00").getTime();
		Timestamp dataHoraT = new Timestamp(datHora);
		ed.setDataHora(dataHoraT);
		ed.setQuantidade(10);
		ed.cadastrarEntrada(ed,"");
	}
	
	@Test
	@Ignore
	public void excluirEntrada() throws SQLException {
		
		EntradaDAO ed = new EntradaDAO(); 
		ed.setIdEntrada(2);
		ed.excluirEntrada(ed,"");
	}
	
	@Test
	public void consultaEntrada() throws SQLException {
		
		EntradaDAO ed = new EntradaDAO();
		ArrayList<Entrada> lista = ed.consultarEntrada("Temporario");
		
		for(Entrada e : lista) {
			
			System.out.println(e.getProduto().getDescricao() + "-" + e.getQuantidade() + "-" + e.getDataHora() + "-"+e.getProduto().getValor().getValor());
		}
		
	}
	
	@Test
	@Ignore
	public void consultaValorProduto() throws SQLException {
		
		ValoresDAO vd = new ValoresDAO();
		Produto p = new Produto();
		p.setIdProduto(2);
		
		ArrayList<Valores> lista = vd.consultarValorCompraProduto(p);
		
		for(Valores v : lista) {
			
			System.out.println(v.getData() + "-----> "+ v.getValor());
		}
		
	}
	
	
	@Test
	@Ignore
	public void consultaValores() throws SQLException {
		
		ValoresDAO vd = new ValoresDAO();		
		
		ArrayList<Valores> lista = vd.consultarValoresProdutos();
		
		for(Valores v : lista) {
			
			System.out.println(v.getDescricaoTpValor() ) ;
		}
		
	}	
	
	@Test
	@Ignore
	public void cadastrarVenda() throws ParseException, SQLException {
		
		VendaDAO vd = new VendaDAO();
		Produto p = new Produto();
		p.setIdProduto(4);		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:MM:yyyy");
		long dataHora = formato.parse("09/01/2019 08:00:00").getTime();
		Timestamp dataHoraT = new Timestamp(dataHora);
		vd.setProduto(p);
		vd.setDatHora(dataHoraT);
		vd.setQuantidade(180);
		vd.setVlrUnitario(180.5F);
		vd.setPerDeconto(10F);
		
		vd.cadastrarVenda(vd);
		
	}
	
	@Test
	@Ignore
	public void excluirVenda() throws SQLException {
				
		VendaDAO vd = new VendaDAO();
		vd.setIdVenda(7);
		vd.excluirVenda(vd,"Permanente");
		
	}
	
	@Test
	@Ignore
	public void consultarProdutoMaisVendido() throws SQLException {
		
		VendaDAO vd = new VendaDAO();
		ArrayList<Venda> lista = vd.consultarProdutoMaisVendido();
		
		for (Venda venda : lista) {
			
			System.out.println(venda.getProduto().getDescricao() + "-" + venda.getQuantidade());
			
		}
		
		
	}
	
	
	@Test
	@Ignore
	public void consultarProdutoMenosVendido() throws SQLException {
		
		VendaDAO vd = new VendaDAO();
		ArrayList<Venda> lista = vd.consultarProdutoMenosVendido();
		
		for (Venda venda : lista) {
			
			System.out.println(venda.getProduto().getDescricao() + "-" + venda.getQuantidade());
			
		}
		
		
	}	
	
	@Test
	@Ignore
	public void consultarValorVendaProduto() throws SQLException {
		
		VendaDAO vd = new VendaDAO();
		ArrayList<Venda> lista = vd.consultarValorVendaProduto();
		
		for (Venda venda : lista) {
			
			System.out.println(venda.getProduto().getDescricao() + "-" + venda.getVlrUnitario());
			
		}
		
		
	}	
	
	@Test
	@Ignore
	public void validaExclusaoProdutoSabor() throws SQLException {
		
		ProdutoDAO pd = new ProdutoDAO();
		Sabor s = new Sabor();
		s.setId(320);
		System.out.println(pd.ValidaExclusaoProdutoSabor(s)); 
		
	}
	
	@Test
	@Ignore
	public void listaComboProduto() throws SQLException {
		
		ProdutoDAO pd = new ProdutoDAO();
		ArrayList<Produto> lista = pd.listarProdutoCombo();
		
		for (Produto produto : lista) {
			
			System.out.println(produto.getDescricao());
			
		}
		
		
	}
	
	@Test
	@Ignore
	public void consultaVendasT() {
		
		VendaDAO vd = new VendaDAO();
		ArrayList<Venda> lista = vd.consultarVendas();
		
		for (Venda venda : lista) {
			
			System.out.println(venda.getProduto().getDescricao() + " - " + venda.getQuantidade());
			
		}
		
	}
	
	@Test
	@Ignore
	public void consultaEstoque() throws SQLException {
		
		ProdutoDAO pd = new ProdutoDAO();
		ArrayList<Estoque> lista = new ArrayList<>();
		lista = pd.consultaEstoque();
		
		for (Estoque estoque : lista) {
			
			System.out.println(estoque.getDescricaoProduto() + " - " + estoque.getQuantidade());
			
		}
		
		
	}
		
	
}
