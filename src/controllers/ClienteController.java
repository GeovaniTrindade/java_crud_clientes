package controllers;

import java.util.List;
import java.util.Scanner;

import entities.Cliente;
import repositories.ClienteRepository;

public class ClienteController {

	public void cadastrarCliente() {

		System.out.println("\n*** CADASTRO DE CLIENTES ***\n");
		Scanner scanner = new Scanner(System.in);

		try {

			Cliente cliente = new Cliente();
			System.out.print("INFORME O NOME.........: ");
			cliente.setNome(scanner.nextLine());
			System.out.print("INFORME O EMAIL........: ");
			cliente.setEmail(scanner.nextLine());
			System.out.print("INFORME O CPF.....: ");
			cliente.setCpf(scanner.nextLine());
			System.out.print("INFORME O TELEFONE..........: ");
			cliente.setTelefone(scanner.nextLine());

			ClienteRepository clienteRepository = new ClienteRepository();
			clienteRepository.create(cliente);

			System.out.println("Cliente cadastrado  com sucesso");

		} catch (Exception e) {
			System.out.println("\nFalha ao cadastrar cliente: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

	// método para realizar um fluxo de edição de cliente
	public void atualizarCliente() {

		System.out.println("\n*** ATUALIZAÇÃO DE CLIENTES ***\n");
		Scanner scanner = new Scanner(System.in);

		try {

			System.out.print("INFORME O ID DO CLIENTE: ");
			Integer idCliente = Integer.parseInt(scanner.nextLine());

			// consultando o cliente no banco de dados através do id
			ClienteRepository clienteRepository = new ClienteRepository();
			Cliente cliente = clienteRepository.findById(idCliente);

			// verificando se o cliente foi encontrado
			if (cliente != null) {

				System.out.print("ALTERE O NOME.........: ");
				cliente.setNome(scanner.nextLine());

				System.out.print("ALTERE O EMAIL........: ");
				cliente.setEmail(scanner.nextLine());

				System.out.print("ALTERE O CPF..........: ");
				cliente.setCpf(scanner.nextLine());

				System.out.print("ALTERE O TELEFONE.....: ");
				cliente.setTelefone(scanner.nextLine());

				// atualizando o cliente no banco de dados
				clienteRepository.update(cliente);

				System.out.println("\nCliente atualizado com sucesso!");

			} else {
				System.out.println("\nCliente não encontrado!");
			}

		} catch (Exception e) {
			System.out.println("\nFalha ao atualizar cliente: " + e.getMessage());

		} finally {
			scanner.close();
		}

	}

	// método para realizar um fluxo de exclusão de clientes
	public void excluirCliente() {

		System.out.println("\n*** EXCLUSÃO DE CLIENTES ***\n");
		Scanner scanner = new Scanner(System.in);

		try {

			System.out.print("INFORME O ID DO CLIENTE: ");
			Integer idCliente = Integer.parseInt(scanner.nextLine());

			// buscando o cliente no banco de dados através do id
			ClienteRepository clienteRepository = new ClienteRepository();
			Cliente cliente = clienteRepository.findById(idCliente);

			// verificando se o cliente foi encontrado
			if (cliente != null) {

				// excluindo o cliente
				clienteRepository.delete(cliente);

				System.out.println("\nCliente excluído com sucesso.");
			} else {
				System.out.println("\nCliente não encontrado.");
			}
		} catch (Exception e) {
			System.out.println("\nFalha ao excluir cliente: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

	// método para realizar um fluxo de consulta de clientes
	public void consultarClientes() {

		System.out.println("\n*** Consulta de Clientes*** \n");

		try {

			ClienteRepository clienteRepository = new ClienteRepository();
			List<Cliente> lista = clienteRepository.findAll();

			// pela lógica de programação
			// for(int i = 0; i < lista.size(); i++) {}

			// no java for each
			for (Cliente cliente : lista) {

				// imprimir os dados de cada cliente
				System.out.println("ID DO CLIENTE....: " + cliente.getIdCliente());
				System.out.println("NOME.............: " + cliente.getNome());
				System.out.println("EMAIL............: " + cliente.getEmail());
				System.out.println("CPF..............: " + cliente.getCpf());
				System.out.println("TELEFONE.........: " + cliente.getTelefone());
				System.out.println("...");
			}
			System.out.println("\nQuantidade de clientes obtidos: " + lista.size());
		} catch (Exception e) {
			System.out.println("\nFalha ao consultar cliente: " + e.getMessage());
		}
	}

}
