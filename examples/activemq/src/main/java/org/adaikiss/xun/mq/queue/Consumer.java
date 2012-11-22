/**
 * 
 */
package org.adaikiss.xun.mq.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * @author hlw
 * 
 */
@Component
public class Consumer implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println(">>message:" + textMessage.getText());
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
