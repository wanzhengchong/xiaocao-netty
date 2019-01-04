package in.xiaocao.echo.netty.unpack.problem;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
	
	public static final int REPEAT = 500;
	
    @Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	for (int i = 0; i < 500; i++) {
    		String output = "Hello, xiaocao" + i;
    		byte[] data = output.getBytes();
    		ByteBuf buf = Unpooled.buffer(data.length);
    		buf.writeBytes(data);
    		ctx.writeAndFlush(buf);
    	}
	}

}
