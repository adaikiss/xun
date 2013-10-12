/**
 * 
 */
package org.adaikiss.xun.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author hlw
 *
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter{

	private ByteBuf buf;
	public EchoServerHandler(){
		buf = PooledByteBufAllocator.DEFAULT.buffer(4);
		buf.writeChar('p');
		buf.writeChar('o');
		buf.writeChar('n');
		buf.writeChar('g');
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println(((ByteBuf)msg).toString(CharsetUtil.UTF_8));
		Thread.sleep(2000);
		ctx.write(buf.copy());
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
}
