package br.com.caelum.jms;

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

        Destination  fila = (Destination) context.lookup("financeiro");

        MessageProducer producer = session.createProducer(fila);

        for(int i = 0; i<10000; i++){
            Message message = session.createTextMessage("<div>"+ i +"</div>");
            producer.send(message);
        }



       new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }
}
