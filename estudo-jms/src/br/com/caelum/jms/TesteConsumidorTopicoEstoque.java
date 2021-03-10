package br.com.caelum.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class TesteConsumidorTopicoEstoque {
    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.setClientID("estoque");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//transaão e confirmação do rebebiento  mensagen

        Topic  topico = (Topic) context.lookup("loja");
        MessageConsumer consumer = session.createDurableSubscriber(topico, "assEstoque");// fica escutando

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
