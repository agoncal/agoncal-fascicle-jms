package org.agoncal.fascicle.jms.puttingtogether;

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
public class OrderProducer {

  public static void main(String[] args) throws NamingException {
    // tag::adocskip[]
    if (args.length != 1) {
      System.out.println("usage : enter an amount");
      System.exit(0);
    }

    System.out.println("Sending message with amount = " + args[0]);
    // end::adocskip[]

    // Creates an orderDto with a total amount parameter
    Float totalAmount = Float.valueOf(args[0]);
    OrderDTO order = new OrderDTO(1234l, new Date(), "Serge Gainsbourg", totalAmount);

    // Gets the JNDI context
    Context jndiContext = new InitialContext();

    // Looks up the administered objects
    ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/fascicle/ConnectionFactory");
    Destination topic = (Destination) jndiContext.lookup("jms/fascicle/Topic");

    try (JMSContext jmsContext = connectionFactory.createContext()) {
      // Sends an object message to the topic
      jmsContext.createProducer().setProperty("orderAmount", totalAmount).send(topic, order);
    }
  }
}
// end::adocsnippet[]
