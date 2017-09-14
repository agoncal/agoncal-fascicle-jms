package org.agoncal.fascicle.jms.producers.ex01;

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
public class Consumer {

  // ======================================
  // =           Public Methods           =
  // ======================================

  public static void main(String[] args) {

    try {
      // Gets the JNDI context
      Context jndiContext = new InitialContext();

      // Looks up the administered objects
      ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/fascicle/ConnectionFactory");
      Destination queue = (Destination) jndiContext.lookup("jms/fascicle/Queue");

      // Loops to receive the messages
      System.out.println("\nInfinite loop. Waiting for a message...");
      try (JMSContext context = connectionFactory.createContext()) {
        while (true) {
          String message = context.createConsumer(queue).receiveBody(String.class);
          System.out.println("Message received: " + message);
        }
      }

    } catch (NamingException e) {
      e.printStackTrace();
    }
  }
}
