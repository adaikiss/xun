/**
 * 
 */
package org.adaikiss.xun.showcase.kafka.sample;

import java.util.Properties;
import java.util.Scanner;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author HuLingwei
 * 
 */
public class HighLevelConsumerExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Properties props = PropertiesLoaderUtils
				.loadAllProperties("application.properties");
		ProducerConfig config = new ProducerConfig(props);
		Producer<String, String> producer = new Producer<String, String>(config);
		Scanner s = new Scanner(System.in);
		String line;
		while (true) {
			line = s.nextLine();
			if ("exit".equals(line.trim())) {
				break;
			}
			KeyedMessage<String, String> data = new KeyedMessage<String, String>(
					"dota", line);
			producer.send(data);
		}
		s.close();
		producer.close();
	}
}
