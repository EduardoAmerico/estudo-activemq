package br.com.caelum.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class TesteConsumidorFila {
    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//transaão e confirmação do rebebiento  mensagen

        Destination  fila = (Destination) context.lookup("financeiro");
        MessageConsumer consumer = session.createConsumer(fila);// fica escutando

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

                TextMessage textMesage = (TextMessage) message;
                try {
                    System.out.println("Recebiada a mensagem: " + textMesage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });



       new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }
}
