package in.xiaocao.echo.netty.decoder.object;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
        	Cat c = (Cat) msg;
        	System.out.println(c);
        	c.setName("[ECHO]" + c.getName());
        	ctx.writeAndFlush(c);
        } finally {
            ReferenceCountUtil.release(msg) ; // 释放缓存
        }
    }

}
