package br.com.caelum.jms.log;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class TesteProdutorFila {
    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//transaão e confirmação do rebebiento  mensagen

        Destination  fila = (Destination) context.lookup("LOG");

        MessageProducer producer = session.createProducer(fila);


            Message message = session.createTextMessage(" INFO | -- It is expected that JMS providers will provide the tools an administrator needs to create and configure administered objects in a JNDI namespace. ");
            producer.send(message,DeliveryMode.NON_PERSISTENT,0,80000);

        message = session.createTextMessage(" ERROR | -- It is expected that JMS providers will provide the tools an administrator needs to create and configure administered objects in a JNDI namespace. ");
        producer.send(message,DeliveryMode.NON_PERSISTENT,9,80000);

        message = session.createTextMessage(" WARNING | -- It is expected that JMS providers will provide the tools an administrator needs to create and configure administered objects in a JNDI namespace. ");
        producer.send(message,DeliveryMode.NON_PERSISTENT,7,80000);

            new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }
}
