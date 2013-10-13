/**
 * 
 */
package org.adaikiss.kay.trys.cxf;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.bind.JAXBElement;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * @author hlw
 * 
 */
public class SmsReceiveServiceClient {
	private static String address = "http://sms.zonesea.cn:8080/axis/services/SmsReviceService";
	private static int outerTimes = 1;
	private static void sendMsg0(int num){
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
		proxyFactory.setAddress(address);
		proxyFactory.setServiceClass(SmsReceiveService.class);
		SmsReceiveService service = (SmsReceiveService) proxyFactory
				.create();
		ObjectFactory factory = new ObjectFactory();
		DeliveryReport deliveryReport = new DeliveryReport();
		deliveryReport.setMessageId(factory
				.createDeliveryReportMessageId("--------------------" + format(num, 5)));
		deliveryReport.setStatusCode(factory
				.createDeliveryReportStatusCode("DELIVRD"));
		service.NotifySmsDeliveryReport(deliveryReport);
	}
	private static void sendMsg(){
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
		proxyFactory.setAddress(address);
		proxyFactory.setServiceClass(SmsReceiveService.class);
		SmsReceiveService service = (SmsReceiveService) proxyFactory
				.create();
		ObjectFactory factory = new ObjectFactory();
		DeliveryReport deliveryReport = new DeliveryReport();
		deliveryReport.setMessageId(factory
				.createDeliveryReportMessageId("f261b31d-f09d-4fd0-9dc7-9b7f0d3cf932"));
		deliveryReport.setStatusCode(factory
				.createDeliveryReportStatusCode("DB:0140"));
		deliveryReport.setReceivedAddress(factory.createDeliveryReportReceivedAddress("13868028903"));
		deliveryReport.setMessageDeliveryStatus(factory.createDeliveryReportMessageDeliveryStatus("DeliveryFailed"));
		service.NotifySmsDeliveryReport(deliveryReport);
	}
	private static class TestThread extends Thread {
		private int num;
		public TestThread(int num){
			this.num = num;
		}
		@Override
		public void run() {
//			sendMsg(num);
			sendMsg();
		}
	}

	private static String format(int num, int length){
		for(int i = 1; i < length; i++){
			if(num < Math.pow(10, i)){
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < length - i; j ++){
					sb.append("0");
				}
				return sb.append(num).toString();
			}
		}
		return String.valueOf(num);
	}
	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newFixedThreadPool(10);
		int max = outerTimes + 1;
		for (int i = 1; i < max; i++) {
			pool.submit(new TestThread(i));
		}
		pool.shutdown();
//		System.out.println(format(10, 1));
//		System.out.println(format(10, 2));
//		System.out.println(format(10, 5));
//		System.out.println(format(100, 5));
//		System.out.println(format(50000, 5));
	}
}
