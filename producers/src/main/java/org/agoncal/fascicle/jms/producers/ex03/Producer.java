package org.agoncal.fascicle.jms.producers.ex03;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.Date;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
// tag::adocsnippet[]
public class Producer {

  @Inject
  @JMSConnectionFactory("jms/fascicle/ConnectionFactory")
  private JMSContext context;

  @Resource(lookup = "jms/fascicle/Queue")
  private Queue queue;

  public void sendMessage() {

    // Sends a text message to the queue
    context.createProducer().send(queue, "JMS 2.0 - This is a text message sent at " + new Date());
  }
}
// end::adocsnippet[]
