package in.xiaocao.echo.netty.decoder.json;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
	
    @Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	Cat c = new Cat();
    	c.setName("Tom");
    	c.setAge(3);
		ctx.writeAndFlush(c);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Cat c = (Cat) msg;
		System.out.println(c);
	}
    

}
