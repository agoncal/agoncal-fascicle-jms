package org.agoncal.fascicle.jms.mdb.ex03;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocsnippet[]
@MessageDriven(mappedName = "jms/javaee6/Topic", activationConfig = {
  @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
  @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "orderAmount < 3000")
})
public class BillingMDB implements MessageListener {

  // tag::adocskip[]
  @Resource
  private MessageDrivenContext context;

  // end::adocskip[]
  public void onMessage(Message message) {
    try {
      System.out.println("Message received: " + message.getBody(String.class));
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}
// end::adocsnippet[]
