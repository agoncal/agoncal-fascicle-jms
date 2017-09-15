package org.agoncal.fascicle.jms.producers.ex02;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.Date;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
// tag::adocsnippet[]
  @Stateless
public class ProducerEJB {

  @Resource(lookup = "jms/fascicle/ConnectionFactory")
  private ConnectionFactory connectionFactory;
  @Resource(lookup = "jms/fascicle/Queue")
  private Queue queue;

  public void sendMessage() {

    try (JMSContext context = connectionFactory.createContext()) {
      // Sends a text message to the queue
      context.createProducer().send(queue, "JMS 2.0 - This is a text message sent at " + new Date());
    }
  }
}
// end::adocsnippet[]
