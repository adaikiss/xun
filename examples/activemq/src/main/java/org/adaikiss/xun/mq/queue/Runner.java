/**
 * 
 */
package org.adaikiss.xun.mq.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hlw
 * 
 */
public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/jms/queue.xml");
		Producer producer = context.getBean(Producer.class);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter your message: ");
		String message = null;
		do {
			try {
				message = reader.readLine();
				producer.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}while(!"exit".equals(message));
		try {
			reader.close();
		} catch(Exception e){
		}
		System.exit(0);
	}
}
