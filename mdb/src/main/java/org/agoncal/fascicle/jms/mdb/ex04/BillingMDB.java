package org.agoncal.fascicle.jms.mdb.ex04;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;
import java.util.Date;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
// tag::adocsnippet[]
@MessageDriven(mappedName = "jms/fascicle/Topic", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "orderAmount BETWEEN 3 AND 7")
})
public class BillingMDB implements MessageListener {

  @Inject
  @JMSConnectionFactory("jms/fascicle/ConnectionFactory")
  @JMSSessionMode(JMSContext.AUTO_ACKNOWLEDGE)
  private JMSContext context;
  @Resource(lookup = "jms/fascicle/Queue")
  private Destination printingQueue;

  public void onMessage(Message message) {
    try {
      System.out.println("Message received: " + message.getBody(String.class));
      sendPrintingMessage();
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }

  private void sendPrintingMessage() throws JMSException {
    context.createProducer().send(printingQueue, "This message has been received and sent again at " + new Date());
  }
}
// end::adocsnippet[]
