package in.xiaocao.echo.netty.decoder.string;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
	
    @Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	String output = "Hello, xiaocao";
		ctx.writeAndFlush(output);
	}

}
