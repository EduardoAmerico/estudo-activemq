package br.com.caelum.jms;


import br.com.caelum.modelo.Pedido;
import br.com.caelum.modelo.PedidoFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.xml.bind.JAXB;
import java.io.StringWriter;
import java.util.Scanner;

public class TesteProdutorTopicoObjeto {
    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//transaão e confirmação do rebebiento  mensagen

        Destination  topico = (Destination) context.lookup("loja");

        MessageProducer producer = session.createProducer(topico);

       // for(int i = 0; i<10000; i++){
       //     Message message = session.createTextMessage("<div> livro </div>");
      //      message.setBooleanProperty("ebook", false);

        Pedido pedido = new PedidoFactory().geraPedidoComValores();


        Message message = session.createObjectMessage(pedido);
            producer.send(message);
   //     }



       new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }
}
