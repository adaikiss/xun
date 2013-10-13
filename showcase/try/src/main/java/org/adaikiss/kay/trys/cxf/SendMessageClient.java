package org.adaikiss.kay.trys.cxf;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class SendMessageClient {
	private static String address = "http://sms.zonesea.cn:8080/axis/services/SendMessage";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
		proxyFactory.setAddress(address);
		proxyFactory.setServiceClass(SendMessage.class);
		SendMessage service = (SendMessage) proxyFactory.create();
		System.out.println(service.sendFreeMessage("aa", "bb", "13868028903", "test", "0"));
	}

}
