package java_demo.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageConsumer {
    private static final String DESTINATION = "myQueue";
    private static final String BROKER_URL = "tcp://127.0.0.1:61616";
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        javax.jms.MessageConsumer messageConsumer = null;
        try {
            connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(DESTINATION);
            messageConsumer = session.createConsumer(destination);

            System.out.println("开始无限轮询...........");
            while (true) {
                Message receive = messageConsumer.receive();
                if (receive instanceof TextMessage) {
                    String text = ((TextMessage) receive).getText();
                    System.out.println("接收到的消息：" + text);
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (messageConsumer != null) {
                    messageConsumer.close();
                }
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
