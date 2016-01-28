package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.Conexao;

public class ClienteDAO {

	/** By Wellington Azevedo
	 * Consulta e retorna a lista de clientes
	 * @return
	 */
	public List<Cliente> getListaClientes() {
		
		List<Cliente> lista = new ArrayList<>();
		
		try {
			Connection conexao = Conexao.getMySQLConnection();

			String sql = "SELECT * FROM cliente;";
			
			PreparedStatement comando = conexao.prepareStatement(sql);
			ResultSet rs = comando.executeQuery();
			
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nm_cliente"));
				cliente.setEndereco(rs.getString("endereco"));
				lista.add(cliente);
			}

			comando.close();
			conexao.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	/**
	 * Metodo responsável por inseir um cliente no banco de dados.
	 * 
	 * @param cliente
	 */
	public void inserirCliente (Cliente cliente) {
		
		try {
			Connection conexao = Conexao.getMySQLConnection();
	

			String sql = 
					"INSERT INTO cliente (nm_cliente, endereco) " +
					"VALUES (?, ?);";
			
			// criando um prepared statement
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, cliente.getNome());
			comando.setString(2, cliente.getEndereco());
				
			// executando o prepared statement
			comando.executeUpdate();
			comando.close();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo responsável por atualizar um cliente no banco de dados.
	 * 
	 * @param cliente
	 */
	public void atualizarCliente (Cliente cliente) {
		
		try {
			Connection conexao = Conexao.getMySQLConnection();

			String sql = 
					"UPDATE cliente SET nm_cliente = ?, endereco = ? " +
					"WHERE id_cliente = ?";
			
			// criando um prepared statement
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, cliente.getNome());
			comando.setString(2, cliente.getEndereco());
			comando.setInt(3, cliente.getId());	
			
			// executando o prepared statement
			comando.executeUpdate();
			comando.close();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo responsável por incluir um cliente no banco de dados.
	 * 
	 * @param cliente
	 */
	public void apagarCliente (Cliente cliente) {
		
		try {
			Connection conexao = Conexao.getMySQLConnection();

			String sql = 
					"DELETE FROM cliente WHERE id_cliente = ?;";
			
			// criando um prepared statement
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, cliente.getId());
				
			// executando o prepared statement
			comando.executeUpdate();
			comando.close();
			conexao.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Consultar cliente pelo ID
	 * @param cliente
	 * @return
	 */
	public Cliente detalharCliente (Cliente cliente) {
		
		Cliente clienteRetorno = null;
		try {
			Connection conexao = Conexao.getMySQLConnection();

			String sql = 
					"SELECT nm_cliente, endereco FROM cliente WHERE id_cliente = ?;";
			
			// criando um prepared statement
			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setInt(1, cliente.getId());
				
			// executando o prepared statement
			ResultSet rs = comando.executeQuery();
			
			while (rs.next()) {
				clienteRetorno = new Cliente();
				clienteRetorno.setId(rs.getInt(1));
				clienteRetorno.setNome(rs.getString(2));
				clienteRetorno.setEndereco(rs.getString(3));
			}
			
			comando.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clienteRetorno;
	}

}
