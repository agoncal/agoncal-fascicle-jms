package org.agoncal.fascicle.jms.apis.ex01;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Date;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
// tag::adocsnippet[]
public class Producer {

  public static void main(String[] args) {

    try {
      // Gets the JNDI context
      Context jndiContext = new InitialContext();

      // Looks up the administered objects
      ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/fascicle/ConnectionFactory");
      Destination queue = (Destination) jndiContext.lookup("jms/fascicle/Queue");

      // Creates the needed artifacts to connect to the queue
      Connection connection = connectionFactory.createConnection();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      MessageProducer producer = session.createProducer(queue);

      // Sends a text message to the queue
      TextMessage message = session.createTextMessage("JMS 1.1 - This is a text message sent at " + new Date());
      producer.send(message);
      System.out.println("\nMessage sent !");

      connection.close();

    } catch (NamingException | JMSException e) {
      e.printStackTrace();
    }

    System.exit(0);
  }
}
// end::adocsnippet[]
