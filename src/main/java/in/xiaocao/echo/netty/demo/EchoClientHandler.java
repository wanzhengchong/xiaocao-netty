package in.xiaocao.echo.netty.demo;

import java.util.Date;

import in.xiaocao.echo.util.InputUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 
 * @ClassName: EchoClientHandler
 * @Description: 客户端的输入处理器
 * @author zhengchong.wan
 * @date 2019年1月4日 下午7:16:15
 *
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("client channelActive" + new Date());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 只要服务器端发送完成信息之后，都会执行此方法进行内容的输出操作
		try {
			ByteBuf byteBuf = (ByteBuf) msg;
			String readData = byteBuf.toString(CharsetUtil.UTF_8);
			if ("quit".equalsIgnoreCase(readData)) {
				System.out.println("[EXIT]byebye!");
				ctx.close();
			} else {
				System.out.println(readData);
				String inputData = InputUtil.getString("please input:");
				byte[] data = inputData.getBytes();
				ByteBuf sendBuf = Unpooled.buffer(data.length);
				sendBuf.writeBytes(data);
				ctx.writeAndFlush(sendBuf);
			}
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
