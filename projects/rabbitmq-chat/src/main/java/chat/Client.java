package chat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Client {
    public static void main(String[] args) {
        System.out.print("Enter your name: ");
        String name = Command.name();

        String exchange_name = "chat";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            new ClientConsumer().run();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e.getMessage());
        }

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(exchange_name, BuiltinExchangeType.FANOUT);

            Gson gson = new Gson();
            while(true) {
                System.out.print("-> ");
                String content = null;
                try {
                    content = Command.message();
                } catch(Exception e) {
                    content = Command.message();
                }

                if(content.equals("/exit")) {
                    System.out.println("Exit to application...");
                    break;
                }

                Date dateAndHourNow = new Date();
                String date = new SimpleDateFormat("dd/MM/yyyy").format(dateAndHourNow);
                String hour = new SimpleDateFormat("HH:mm:ss").format(dateAndHourNow);
                Message message = new Message(name, content, date, hour);
                channel.basicPublish(exchange_name, "", null, gson.toJson(message).getBytes());
            }
        } catch(TimeoutException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
