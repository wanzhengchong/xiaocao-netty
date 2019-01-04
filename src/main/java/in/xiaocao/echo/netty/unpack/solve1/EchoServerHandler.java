package in.xiaocao.echo.netty.unpack.solve1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	
	private int index = 0;
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
        	ByteBuf buf = (ByteBuf) msg;
        	String inputData = buf.toString(CharsetUtil.UTF_8);
        	System.out.println((index ++) + inputData);
        } finally {
            ReferenceCountUtil.release(msg) ; // 释放缓存
        }
    }

}
