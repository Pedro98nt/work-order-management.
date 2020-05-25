package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.conexao.Conexao;
import br.com.faculdadedelta.modelo.ClientePedro;
import br.com.faculdadedelta.modelo.OrdemPedro;
import br.com.faculdadedelta.modelo.ServicoPedro;

public class OrdemDaoPedro {

	public void incluir(OrdemPedro ordemPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " INSERT INTO ordem_servico ( id_cliente,id_servico,valor_unitario_ordem_servico,qtde_ordem_servico,valor_desconto,valor_total_ordem_servico)"
				+ "VALUES(?,?,?,?,?,?)";
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(sql);
			ps.setLong(1, ordemPedro.getCliente().getId());
			ps.setLong(2, ordemPedro.getServico().getId());
			ps.setDouble(3, ordemPedro.getValorUnitario());
			ps.setInt(4, ordemPedro.getQuantidade());
			ps.setDouble(5, ordemPedro.getValorDesconto());
			ps.setDouble(6, ordemPedro.getValorTotal());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(OrdemPedro ordemPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " UPDATE ordem_servico " + "SET valor_unitario_ordem_servico =?," + " qtde_ordem_servico=?,"
				+ " valor_desconto=?," + "valor_total_ordem_servico=?, " + " id_cliente= ?," + "id_servico=?, "
				+ "WHERE id_ordem_servico=?";

		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setDouble(1, ordemPedro.getValorUnitario());
			ps.setInt(2, ordemPedro.getQuantidade());
			ps.setDouble(3, ordemPedro.getValorDesconto());
			ps.setDouble(4, ordemPedro.getValorTotal());
			ps.setLong(5, ordemPedro.getCliente().getId());
			ps.setLong(6, ordemPedro.getServico().getId());
			ps.setLong(7, ordemPedro.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(OrdemPedro ordemPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " DELETE FROM ordem_servico WHERE id_ordem_servico=? ";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setLong(1, ordemPedro.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}


	public List<OrdemPedro>listar() throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT "
				+"o.id_ordem_servico AS idOrdem  "
				+"o.valor_unitario_ordem_servico AS valorOrdem "
				+"o.qtde_ordem_servico AS quantOrdem"
				+"o.valor_desconto AS valorDesconto  "
				+ "o.valor_total_ordem_servico AS valorTotal"
				+"cl.id_cliente AS idCliente"
				+"cl.nome_cliente AS nomeCliente "
				+" cl.idade_cliente AS idadeCliente "
				+"sr.id_servico AS idServico "
				+"sr.nome_servico AS nomeServico "
				+"sr.descricao_servico AS descricaoServico"
				+"FROM ordem_servico o"
				+" INNER JOIN clientes cl ON o.id_cliente =cl.id_cliente"
				+ "INNER JOIN servico sr ON o.id_servico = sr.id_servico  ";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OrdemPedro>listaRetorno = new ArrayList<OrdemPedro>();
	
		try {
			ps = conn.prepareStatement(sql);
			rs= ps.executeQuery();
			while(rs.next()) {
				OrdemPedro ordemPedro = new OrdemPedro();
				ordemPedro.setId(rs.getLong("idOrdem"));
				ordemPedro.setValorUnitario(rs.getDouble("valorOrdem"));
				ordemPedro.setQuantidade(rs.getInt("quantOrdem"));
				ordemPedro.setValorDesconto(rs.getDouble("valorDesconto"));
				ordemPedro.setValorTotal(rs.getDouble("valorTotal"));
				
				ClientePedro clientePedro = new ClientePedro();
				clientePedro.setId(rs.getLong("idCliente"));
				clientePedro.setNome(rs.getString("nomeCliente"));
				clientePedro.setIdade(rs.getInt("idadeCliente"));
				
				ServicoPedro servicoPedro = new ServicoPedro();
				servicoPedro.setId(rs.getLong("idServico"));
				servicoPedro.setNome(rs.getString("nomeServico"));
				servicoPedro.setDescricao(rs.getString("descricaoServico"));
				
				ordemPedro.setCliente(clientePedro);
				ordemPedro.setServico(servicoPedro);
				
				listaRetorno.add(ordemPedro);
				
				
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		
		return listaRetorno;
		
	}

}
