/**
 * 
 */
package org.adaikiss.xun.concurrency.sync;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * allows to threads to rendezvous and exchange information
 * @author hlw
 *
 */
public class ExchangerShowcase extends Thread{

	private Exchanger<String> exchanger;
	private String data;

	public ExchangerShowcase(String data, Exchanger<String> exchanger){
		this.data = data;
		this.exchanger = exchanger;
	}

	@Override
	public void run(){
		String prev = data;
		try {
			data = exchanger.exchange(data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(prev + "-->" + data);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Exchanger<String> exchanger = new Exchanger<String>();
		executor.submit(new ExchangerShowcase("A", exchanger));
		executor.submit(new ExchangerShowcase("B", exchanger));
		executor.shutdown();
	}

}
