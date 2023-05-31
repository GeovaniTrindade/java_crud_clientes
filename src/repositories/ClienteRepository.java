package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Cliente;
import factories.ConnectionFactory;

public class ClienteRepository {

	// metodo para criar um cliente
	public void create(Cliente cliente) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// escerevendo uma instrução SQL para inserir um clientena base de dados
		PreparedStatement statement = connection
				.prepareStatement("insert into tb_cliente(nome, email, cpf, telefone) values(?,?,?,?)");

		statement.setString(1, cliente.getNome());
		statement.setString(2, cliente.getEmail());
		statement.setString(3, cliente.getCpf());
		statement.setString(4, cliente.getTelefone());
		statement.execute();

		// fechando a conexão
		connection.close();

	}

	// método para atualizar um cliente no banco de dados
	public void update(Cliente cliente) throws Exception {
		// abrindo a conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// instrução SQL para atualizar um cliente na base de dados
		PreparedStatement statement = connection
				.prepareStatement("update tb_cliente set nome=?, email=?, cpf=?, telefone=? where idcliente=?");
		statement.setString(1, cliente.getNome());
		statement.setString(2, cliente.getEmail());
		statement.setString(3, cliente.getCpf());
		statement.setString(4, cliente.getTelefone());
		statement.setInt(5, cliente.getIdCliente());
		statement.execute();

		// fechando conexão
		statement.close();
	}

	// método para excluir um cliente no banco de dados
		public void delete(Cliente cliente) throws Exception {
			
			//abrindo conexão com o banco de dados
			Connection connection = ConnectionFactory.getConnection();
			
			//escrevendo uma instrução SQL para excluir um cliente na base de dados
			PreparedStatement statement = connection.prepareStatement
					("delete from tb_cliente where idcliente=?");
			statement.setInt(1, cliente.getIdCliente());
			statement.execute();
			
			//fechando a conexão
			connection.close();
		}

	// método para consultar todos os clientes cadastrados
	public List<Cliente> findAll() throws Exception {

		// abrir conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// instrução para consultar todos os clientes cadastrados
		PreparedStatement statement = connection.prepareStatement("select * from tb_cliente");
		ResultSet rs = statement.executeQuery();

		// criando uma lista de clientes
		List<Cliente> list = new ArrayList<Cliente>();

		// lendo cada resgistro contido no ResultSet
		while (rs.next()) {

			// criando um objeto cliente
			Cliente cliente = new Cliente();

			cliente.setIdCliente(rs.getInt("idcliente"));
			cliente.setNome(rs.getString("nome"));
			cliente.setEmail(rs.getString("email"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setTelefone(rs.getString("telefone"));

			list.add(cliente); // armazenando cada cliente dentro da lista
		}
		connection.close(); // fechando conexão
		return list; // retornando lista de clientes
	}

	// metodo para consultar 1 cliente através do id (primary key)
	public Cliente findById(Integer idCliente) throws Exception {

		// abrir conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// instrução para consultar 1 cliente através do id
		PreparedStatement statement = connection.prepareStatement("select * from tb_cliente where idcliente=?");
		statement.setInt(1, idCliente);
		ResultSet rs = statement.executeQuery();

		// criando um objeto Cliente vazio
		Cliente cliente = null;

		// verificando se algum registro foi encontrado
		if (rs.next()) {

			// instanciar o objeto cliente
			cliente = new Cliente();

			cliente.setIdCliente(rs.getInt("idcliente"));
			cliente.setNome(rs.getString("nome"));
			cliente.setEmail(rs.getString("email"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setTelefone(rs.getString("telefone"));

		}

		connection.close();
		return cliente;

	}

}
