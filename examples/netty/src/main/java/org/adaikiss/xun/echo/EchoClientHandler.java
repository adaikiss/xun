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
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

	private ByteBuf buf;
	public EchoClientHandler(){
		buf = PooledByteBufAllocator.DEFAULT.buffer(4);
		buf.writeChar('p');
		buf.writeChar('i');
		buf.writeChar('n');
		buf.writeChar('g');
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(buf.copy());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println(((ByteBuf)msg).toString(CharsetUtil.UTF_8));
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
