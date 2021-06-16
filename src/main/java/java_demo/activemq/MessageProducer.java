package java_demo.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageProducer {
    private static final String DESTINATION = "myQueue";
    private static final String BROKER_URL = "tcp://127.0.0.1:61616";
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        javax.jms.MessageProducer messageProducer = null;
        try {
            connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
            connection = connectionFactory.createConnection();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            String msg = "Hello ActiveMQ!!!!!!&& yes you are!&&你可真是个小笨比！&& 测试监听器~！";
            Destination queue = session.createQueue(DESTINATION);
            TextMessage textMessage = session.createTextMessage(msg);
            messageProducer = session.createProducer(queue);
            messageProducer.send(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (messageProducer != null) {
                    messageProducer.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (session != null) {
                    session.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
