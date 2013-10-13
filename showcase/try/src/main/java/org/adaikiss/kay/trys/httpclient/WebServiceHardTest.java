/**
 * 
 */
package org.adaikiss.kay.trys.httpclient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * @author hlw
 *
 */
public class WebServiceHardTest {

	private static final String uri = "http://sms.zonesea.cn:8080/axis/services/SmsReviceService";
	private static class ClientThread extends Thread{
		@Override
		public void run(){
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(uri);
			int i = 10;
			while(i-- >0){
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				try {
					String response = httpclient.execute(httpget, responseHandler);
					System.out.println(response);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			httpclient.getConnectionManager().shutdown();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		for(int i = 0; i < 10; i++){			
			new ClientThread().start();
//		}
	}

}
