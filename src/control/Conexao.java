package control;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;

public class Conexao {

	//By Wellington Azevedo
	 // Estabelece conexão com banco MySQL
	 
	public static Connection getMySQLConnection() {
		
		String urlDeConexao = "jdbc:mysql://localhost/seatec";
		String usuario = "root";
		String senha = "admin";
		Connection conexao = null;
		
		try {
			conexao = DriverManager.getConnection(urlDeConexao, usuario, senha);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conexao;
	}
}