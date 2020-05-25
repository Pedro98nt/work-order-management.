package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.conexao.Conexao;
import br.com.faculdadedelta.modelo.ClientePedro;

public class ClienteDaoPedro {

	public void incluir(ClientePedro clientePedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO clientes (nome_cliente ,idade_cliente ) VALUES(?,?)";
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, clientePedro.getNome().trim());
			ps.setInt(2, clientePedro.getIdade());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(ClientePedro clientePedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " UPDATE  clientes " + " SET nome_cliente =?, " + "  idade_cliente=?," + " WHERE id_cliente =?,";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, clientePedro.getNome().trim());
			ps.setInt(2, clientePedro.getIdade());
			ps.setLong(3, clientePedro.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}

	}

	public void excluir(ClientePedro clientePedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM clientes WHERE id_cliente=?, ";
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(sql);

			ps.setLong(1, clientePedro.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public List<ClientePedro> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT id_cliente, nome_cliente,idade_cliente FROM clientes ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ClientePedro> listaRetorno = new ArrayList<ClientePedro>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ClientePedro clientePedro = popularCliente(rs);
				listaRetorno.add(clientePedro);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}

		return listaRetorno;

	}

	private ClientePedro popularCliente(ResultSet rs) throws SQLException {
		ClientePedro clientePedro = new ClientePedro();
		if (rs.next()) {
			clientePedro.setId(rs.getLong("id_cliente"));
			clientePedro.setNome(rs.getString("nome_cliente"));
			clientePedro.setIdade(rs.getInt("idade_cliente"));

		}
		return clientePedro;
	}
	
	public ClientePedro pesquisarPorId (Long id) throws Exception {
		Connection conn  = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT id_cliente, nome_cliente, idade_cliente FROM clientes WHERE id_cliente=? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ClientePedro retorno = new ClientePedro();
		
		try {
			ps= conn.prepareStatement(sql);
			rs= ps.executeQuery();
			ps.setLong(1, id);
			
			if(rs.next()) {
				retorno = popularCliente(rs);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		
		
		return retorno;
		
	}

}