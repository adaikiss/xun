/**
 * 
 */
package org.adaikiss.xun.websocket.jetty;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.jetty.websocket.api.WebSocketConnection;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hlw
 *
 */
@WebSocket(inputBufferSize = 64 * 1024, maxMessageSize = 64 * 1024)
public class JettyChatSocket {

	private static final Logger logger = LoggerFactory.getLogger(JettyChatSocket.class);

	private static ConcurrentLinkedQueue<WebSocketConnection> clients = new ConcurrentLinkedQueue<WebSocketConnection>();

	@OnWebSocketMessage
	public void onWebSocketBinary(WebSocketConnection connection, byte[] payload, int offset, int len) {
		/* only interested in text messages */
	}

	@OnWebSocketClose
	public void onWebSocketClose(WebSocketConnection connection, int statusCode, String reason) {
		clients.remove(connection);
	}

	@OnWebSocketConnect
	public void onWebSocketConnect(WebSocketConnection connection) {
		clients.add(connection);
	}

//	@OnWebSocketError
//	public void onError(Throwable t){
//		
//	}

	@OnWebSocketMessage
	public void onWebSocketText(WebSocketConnection connection, String message) {
		for(WebSocketConnection client : clients){
			try {
				if(client.isOpen()){
					client.write(message);
				}
			} catch (Exception e) {
				logger.error("error on responsing!", e);
			}
		}
	}

}
