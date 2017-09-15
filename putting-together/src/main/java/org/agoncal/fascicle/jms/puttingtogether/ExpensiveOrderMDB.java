package org.agoncal.fascicle.jms.puttingtogether;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// tag::adocsnippet[]
@MessageDriven(mappedName = "jms/fascicle/Topic", activationConfig = {
  @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
  @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "orderAmount > 1000")
})
@JMSConnectionFactoryDefinition(name = "jms/fascicle/ConnectionFactory",
  className = "javax.jms.ConnectionFactory")
@JMSDestinationDefinition(name = "jms/fascicle/Topic",
  className = "javax.jms.Topic", interfaceName = "javax.jms.Topic")
public class ExpensiveOrderMDB implements MessageListener {

  public void onMessage(Message message) {
    try {
      OrderDTO order = message.getBody(OrderDTO.class);
      System.out.println("Expensive order received: " + order.toString());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}
// end::adocsnippet[]
