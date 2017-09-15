package org.agoncal.fascicle.jms.consumers.ex01;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocsnippet[]
public class Listener implements MessageListener {

  public static void main(String[] args) {

    try {
      // Gets the JNDI context
      Context jndiContext = new InitialContext();

      // Looks up the administered objects
      ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/fascicle/ConnectionFactory");
      Destination queue = (Destination) jndiContext.lookup("jms/fascicle/Queue");

      try (JMSContext context = connectionFactory.createContext()) {
        context.createConsumer(queue).setMessageListener(new Listener());
      }

    } catch (NamingException e) {
      e.printStackTrace();
    }
  }

  public void onMessage(Message message) {
    try {
      System.out.println("Async Message received: " + message.getBody(String.class));
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}
// end::adocsnippet[]
