package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.conexao.Conexao;
import br.com.faculdadedelta.modelo.ServicoPedro;

public class ServicoDaoPedro {

	public void incluir(ServicoPedro servicoPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " INSERT INTO servico (nome_servico,descricao_servico ) VALUES(?,?) ";
		PreparedStatement ps = null;

		try {

			ps = conn.prepareStatement(sql);
			ps.setString(1, servicoPedro.getNome().trim());
			ps.setString(2, servicoPedro.getDescricao().trim());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void alterar(ServicoPedro servicoPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE servico" + "SET nome_servico=?," + "descricao_servico=?," + "WHERE id_servico=?";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, servicoPedro.getNome().trim());
			ps.setString(2, servicoPedro.getDescricao().trim());
			ps.setLong(3, servicoPedro.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}

	}

	public void excluir(ServicoPedro servicoPedro) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM servico WHERE id_servico=?";
		PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(sql);

			ps.setLong(1, servicoPedro.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public ServicoPedro popularServico(ResultSet rs) throws SQLException {
		ServicoPedro servicoPedro = new ServicoPedro();

		if (rs.next()) {
			servicoPedro.setId(rs.getLong("id_servico"));
			servicoPedro.setNome(rs.getString("nome_servico"));
			servicoPedro.setDescricao(rs.getString("descricao_servico"));
		}

		return servicoPedro;

	}

	public List<ServicoPedro> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_servico,nome_servico,descricao_servico FROM servico ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ServicoPedro> listaRetorno = new ArrayList<ServicoPedro>();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				ServicoPedro servicoPedro = popularServico(rs);
				listaRetorno.add(servicoPedro);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);

		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}

		return listaRetorno;

	}
	public ServicoPedro pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT id_servico,nome_servico,descricao_servico FROM servico WHERE id_servico =? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ServicoPedro retorno = new ServicoPedro();
		
		try {
			ps= conn.prepareStatement(sql);
			rs= ps.executeQuery();
			ps.setLong(1, id);
			
			if(rs.next()) {
				
				retorno= popularServico(rs);
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
