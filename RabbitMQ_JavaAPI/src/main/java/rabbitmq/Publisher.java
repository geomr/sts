package rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		topic();
//		direct();
		fanout();
		
	}
	private static void fanout() throws IOException, TimeoutException {
		Connection connection = Conexao.getConexao();
		Channel channel = connection.createChannel();
//		channel.queueDeclare("eclipse.fila1", true, false, false,null);
		
		String exchange = "exchange.pedidos";
		
//		channel.exchangeDeclare(exchange, "topic");
        
//		/*Publicar (publish) mensagem */
		for (int i = 0; i < 2; i++) {
			channel.basicPublish(exchange, "", null,  ("Minha mensagem no tópico " + (i+1) ).getBytes());
			System.out.println("Mensage Enviadas ao Topic");
		}
		
		channel.close();
		connection.close();
	}

	private static void topic() throws IOException, TimeoutException {
		Connection connection = Conexao.getConexao();
		Channel channel = connection.createChannel();
//		channel.queueDeclare("eclipse.fila1", true, false, false,null);
		
		String exchange = "exchange.pedidos";
		String rota = "route.pedidos.2c";
		
//		channel.exchangeDeclare(exchange, "topic");
//		String queueName = channel.queueDeclare().getQueue();
        
//		/*Publicar (publish) mensagem */
		for (int i = 0; i < 2; i++) {
			channel.basicPublish(exchange, rota, null,  ("Minha mensagem no tópico " + (i+1) ).getBytes());
			System.out.println("Mensage Enviadas ao Topic");
		}
		
		channel.close();
		connection.close();
	}

	
	private static void direct() throws IOException, TimeoutException {
		Connection connection = Conexao.getConexao();
		/* Criar um canal de comunicacao */
		Channel channel = connection.createChannel();
		
//		channel.queueDeclare("eclipse.fila1", true, false, false,null);
		
		/*Publicar (publish) mensagem */
		for (int i = 0; i < 10; i++) {
			channel.basicPublish("exchange.pedidos", "route.pedidos", null, ("Minha mensagem " + (i+1) ).getBytes());
		}

		System.out.println("Mensagens Enviadas Direct");
		channel.close();
		connection.close();
	}

}
