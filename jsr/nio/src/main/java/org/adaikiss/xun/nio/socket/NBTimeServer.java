/**
 * 
 */
package org.adaikiss.xun.nio.socket;

import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author hlw
 *
 */
public class NBTimeServer {

	private int port;

	private NBTimeServer(int port) throws Exception{
		this.port = port;
		acceptConnections();
	}

	private void acceptConnections() throws Exception{
		Selector acceptSelector = Selector.open();
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(port));
		serverSocketChannel.register(acceptSelector, SelectionKey.OP_ACCEPT);
		while(acceptSelector.select() > 0){
			Set<SelectionKey> readyKeys = acceptSelector.selectedKeys();
			Iterator<SelectionKey> i = readyKeys.iterator();
			while(i.hasNext()){
				SelectionKey sk = i.next();
				i.remove();
				ServerSocketChannel readyChannel = (ServerSocketChannel)sk.channel();
				Socket socket = readyChannel.accept().socket();
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
				Date now = new Date();
				writer.println(now);
				writer.close();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new NBTimeServer(12345);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
