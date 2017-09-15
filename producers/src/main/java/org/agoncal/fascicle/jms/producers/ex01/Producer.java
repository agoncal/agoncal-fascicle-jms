package org.agoncal.fascicle.jms.producers.ex01;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Date;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
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

      try (JMSContext context = connectionFactory.createContext()) {
        // Sends a text message to the queue
        context.createProducer().send(queue, "JMS 2.0 - This is a text message sent at " + new Date());
        System.out.println("\nMessage sent !");
      }

    } catch (NamingException e) {
      e.printStackTrace();
    }

    System.exit(0);
  }
}
// end::adocsnippet[]
