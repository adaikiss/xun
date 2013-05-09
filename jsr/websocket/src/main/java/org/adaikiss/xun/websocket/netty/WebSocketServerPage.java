/**
 * 
 */
package org.adaikiss.xun.websocket.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author hlw
 *
 */
public final class WebSocketServerPage {

    public static ByteBuf getContent(String uri) {
    	String basePath = WebSocketServerPage.class.getResource("/").getPath();
    	basePath = basePath.substring(0, basePath.indexOf("target/")) + "src/main/webapp";
    	FileInputStream stream = null;
    	FileChannel channel = null;
    	StringBuilder sb = new StringBuilder();
    	try {
			stream = new FileInputStream(basePath + uri);
			channel = stream.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(6 * 1024);
			while(channel.read(buffer) != -1){
				buffer.flip();
				sb.append(new String(buffer.array(), "UTF-8"));
				buffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(stream != null){
				try {
					stream.close();
				} catch (IOException e) {
				}
			}
			if(channel != null){
				try {
					channel.close();
				} catch (IOException e) {
				}
			}
		}
    	String content = sb.toString();
        return Unpooled.copiedBuffer(
                content, CharsetUtil.UTF_8);
    }

    private WebSocketServerPage() {
        // Unused
    }
}
