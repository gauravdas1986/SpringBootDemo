package com.cogni.SpringBootDemo;

import java.io.IOException;

import org.springframework.util.SerializationUtils;

import com.cogni.apartment.model.MaintenanceDTO;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MyConsumer {

	private final static String QUEUE_NAME = "TEST_QUEUE";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				MaintenanceDTO dto = (MaintenanceDTO) SerializationUtils.deserialize(body);
				System.out.println(" [x] Received '" + dto.getKey() + "'");
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}