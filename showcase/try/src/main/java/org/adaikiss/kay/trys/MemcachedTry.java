/**
 * 
 */
package org.adaikiss.kay.trys;

import java.io.IOException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;

/**
 * @author hlw
 *
 */
public class MemcachedTry {

	public static MemcachedClient getClient(){
		return getClient("192.168.3.64", 11211);
	}
	public static MemcachedClient getClient(String host, int port){
		MemcachedClient client = null;
		try {
			client = new XMemcachedClient(host, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return client;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		MemcachedClient client = getClient();
		//client.add("test", 300, "存30秒可以吗？");
		System.out.println(client.get("test"));
//		Thread.sleep(33000);
//		System.out.println(client.get("test"));
		System.exit(0);
	}

}
