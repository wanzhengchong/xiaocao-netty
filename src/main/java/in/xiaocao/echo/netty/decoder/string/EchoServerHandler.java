package in.xiaocao.echo.netty.decoder.string;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
        	System.out.println(msg.toString());
        } finally {
            ReferenceCountUtil.release(msg) ; // 释放缓存
        }
    }

}
