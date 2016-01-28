package view;

import java.util.*;

import control.Conexao;
import model.Cliente;
import model.ClienteDAO;

public class Principal {

	public static void main(String[] args){
		
		Scanner teclado = new java.util.Scanner(System.in);
		
		Conexao.getMySQLConnection();
		
		int opcao;
		
		while(true){
	
			System.out.println("\nDigite a Nº da opção que deseja:");
			System.out.println("1-Inserir cliente  2-Deletar cliente  3-Listar Clientes  4-Alterar dados");
			opcao = teclado.nextInt();
			
			if(opcao == 1){
				
				Scanner ler = new java.util.Scanner(System.in);
				
				System.out.println("Digite o nome :");
				String nome = ler.nextLine();
				System.out.println("Digite o endereço");
				String endereco = ler.nextLine();
				
				Cliente cliente = new Cliente();
				
				cliente.setNome(nome);
				cliente.setEndereco(endereco);
				
				ClienteDAO clienteDAO = new ClienteDAO();
				clienteDAO.inserirCliente(cliente);
				System.out.println("Cliente inserido com sucesso");
			}
			if(opcao == 2){
				
				System.out.println("Digite o número de ID do cliente que voce deseja excluir: ");
				int id = teclado.nextInt();
				
				Cliente apagar = new Cliente();
				apagar.setId(id);
				ClienteDAO clienteDAO = new ClienteDAO();
				clienteDAO.apagarCliente(apagar);
				
				System.out.println("Cliente excluído com sucesso");
				
			}
			if(opcao == 3){
				
				ClienteDAO clienteDAO = new ClienteDAO();
				
				for(Cliente c : clienteDAO.getListaClientes()){
					
					System.out.println(c.getId()+ " || " + c.getNome() + " ||"+c.getEndereco());
					
				}
				
			}
			if(opcao == 4){
				
				Scanner leitura = new java.util.Scanner(System.in);
				
				System.out.println("Digite o número da ID: ");
				int id = leitura.nextInt();
				
				Cliente atualizaCliente = new Cliente();
				
				System.out.println("Digite o novo endereço: ");
				String endereco = leitura.nextLine();
				System.out.println("Digite o novo nome: ");
				String nome = leitura.nextLine();
				
				atualizaCliente.setId(id);
				atualizaCliente.setNome(nome);
				atualizaCliente.setEndereco(endereco);
				
				ClienteDAO clienteDAO = new ClienteDAO();
				
				clienteDAO.atualizarCliente(atualizaCliente);
				leitura.close();
			}
			
		}
		
	}	
}
