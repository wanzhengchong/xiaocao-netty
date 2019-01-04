package in.xiaocao.echo.netty.unpack.problem;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 
 * @ClassName: EchoServerHandler 
 * @Description: 会出现粘包现象
 * @author zhengchong.wan
 * @date 2019年1月4日 下午7:46:42
 *
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	
	private int index = 0;
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
        	ByteBuf buf = (ByteBuf) msg;
        	String inputData = buf.toString(CharsetUtil.UTF_8);
        	System.out.println((index++) + inputData);
        } finally {
            ReferenceCountUtil.release(msg) ; // 释放缓存
        }
    }

}
