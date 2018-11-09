import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import rabbitmq.Conexao;

public class ConsumerFanout {

	public static void main(String[] args) throws IOException, TimeoutException {
		
		Connection connection = Conexao.getConexao();
		Channel channel = connection.createChannel();
		String exchange = "exchange.pedidos";
//		channel.exchangeDeclare(exchange, "topic");
	    String queueName = channel.queueDeclare().getQueue();
	    channel.queueBind(queueName, exchange, "");

	    Consumer consumer = new DefaultConsumer(channel) {
	      @Override
	      public void handleDelivery(String consumerTag, Envelope envelope,AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println("Fanout 1:");
	    	  	System.out.println(consumerTag);
				System.out.println(envelope);
				System.out.println(properties);
				System.out.println(new String(body,"UTF-8"));
				
				System.out.println(); System.out.println(); System.out.println();
	      }
	    };
	    channel.basicConsume(queueName, true, consumer);
	  }
}
