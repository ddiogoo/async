package chat;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ClientConsumer extends Thread {
    Channel channel = null;
    private static String QUEUE_NAME;
    private static String EXCHANGE_NAME = "chat";
    private static Gson gson = new Gson();

    public ClientConsumer() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        QUEUE_NAME = channel.queueDeclare().getQueue();
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String content = new String(delivery.getBody(), "UTF-8");
                Message message = gson.fromJson(content, Message.class);
                System.out.println("[" + message.sender() + "]: " + message.content() + " [" + message.date() + ": " + message.hour() + "]");
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
