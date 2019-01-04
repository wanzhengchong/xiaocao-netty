package in.xiaocao.echo.netty.unpack.solve1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * 
 * @ClassName: EchoClient 
 * @Description: 使用换行符解决拆包粘包问题
 * @author zhengchong.wan
 * @date 2019年1月4日 下午8:10:27
 *
 */
public class EchoClient {
    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup(); // 创建一个线程池
        try {
            Bootstrap client = new Bootstrap(); // 创建客户端处理程序
            client.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true) // 允许接收大块的返回数据
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            socketChannel.pipeline().addLast(new EchoClientHandler()); // 追加了处理器
                        }
                    });
            ChannelFuture channelFuture = client.connect("localhost", 9999).sync();
            channelFuture.channel().closeFuture().sync(); // 关闭连接
        } finally {
            group.shutdownGracefully();
        }
    }
    
    public static void main(String[] args) throws Exception {
		new EchoClient().run();
	}
}
