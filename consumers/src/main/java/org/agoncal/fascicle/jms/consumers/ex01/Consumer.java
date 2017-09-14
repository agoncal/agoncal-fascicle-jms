package org.agoncal.fascicle.jms.consumers.ex01;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocsnippet[]
public class Consumer {

  public static void main(String[] args) {

    try {
      // Gets the JNDI context
      Context jndiContext = new InitialContext();

      // Looks up the administered objects
      ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/fascicle/ConnectionFactory");
      Destination queue = (Destination) jndiContext.lookup("jms/fascicle/Queue");

      // Loops to receive the messages
      try (JMSContext context = connectionFactory.createContext()) {
        while (true) {
          String message = context.createConsumer(queue).receiveBody(String.class);
        }
      }

    } catch (NamingException e) {
      e.printStackTrace();
    }
  }
}
// end::adocsnippet[]
