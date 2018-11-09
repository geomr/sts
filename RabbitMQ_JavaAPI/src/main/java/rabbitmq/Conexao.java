package rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Conexao {

	public static Connection getConexao() throws IOException, TimeoutException {
		/* Fábrica de conexoes */
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("admin");
		factory.setPassword("admin");
		factory.setVirtualHost("/");
		factory.setHost("localhost");
		factory.setPort(5672);
		
		/* Cria conexao a partir da fábrica */
		Connection connection = factory.newConnection();
		return connection;
	}
}
