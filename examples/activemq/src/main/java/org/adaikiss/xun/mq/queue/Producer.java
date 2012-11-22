/**
 * 
 */
package org.adaikiss.xun.mq.queue;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * @author hlw
 * 
 */
@Component
public class Producer {
	@Resource(name = "queue")
	private Destination destination;

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessage(final String message) {
		this.jmsTemplate.send(this.destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage();
				textMessage.setText(message);
				return textMessage;
			}
		});
	}
}
