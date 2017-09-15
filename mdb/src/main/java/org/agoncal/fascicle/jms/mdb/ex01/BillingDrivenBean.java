package org.agoncal.fascicle.jms.mdb.ex01;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocsnippet[]
@MessageDriven(mappedName = "jms/fascicle/Topic")
public class BillingDrivenBean implements MessageListener {

  public void onMessage(Message message) {
    try {
      System.out.println("Message received: " + message.getBody(String.class));
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}
// end::adocsnippet[]
