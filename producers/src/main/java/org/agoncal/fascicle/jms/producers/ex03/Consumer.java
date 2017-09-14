package org.agoncal.fascicle.jms.producers.ex03;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Consumer {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Resource(lookup = "jms/javaee7/ConnectionFactory")
  private static ConnectionFactory connectionFactory;
  @Resource(lookup = "jms/javaee7/Queue")
  private static Queue queue;

  // ======================================
  // =           Public Methods           =
  // ======================================

  public static void main(String[] args) {

    // Loops to receive the messages
    System.out.println("\nInfinite loop. Waiting for a message...");
    try (JMSContext context = connectionFactory.createContext()) {
      while (true) {
        String message = context.createConsumer(queue).receiveBody(String.class);
        System.out.println("Message received: " + message);
      }
    }
  }
}
