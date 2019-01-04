package in.xiaocao.echo.netty.demo;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 
 * @ClassName: EchoServerHandler
 * @Description: 服务端的输入处理器
 *     Netty是基于NIO的封装与aio没有任何关系    
 * @author zhengchong.wan
 * @date 2019年1月4日 下午7:18:36
 *
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("server channelActive" + new Date());
		byte data[] = "连接已建立".getBytes();
		ByteBuf buf = Unpooled.buffer(data.length);
		buf.writeBytes(data);
		ctx.writeAndFlush(buf);

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			ByteBuf buf = (ByteBuf) msg;
			String inputData = buf.toString(CharsetUtil.UTF_8);
			String echoData = "[ECHO]" + inputData;
			if ("exit".equalsIgnoreCase(inputData)) { // 结束
				echoData = "quit";
			}
			byte[] data = echoData.getBytes();
			ByteBuf echoBuf = Unpooled.buffer(data.length);
			echoBuf.writeBytes(data);
			ctx.writeAndFlush(echoBuf);
		} finally {
			ReferenceCountUtil.release(msg); // 释放缓存
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
