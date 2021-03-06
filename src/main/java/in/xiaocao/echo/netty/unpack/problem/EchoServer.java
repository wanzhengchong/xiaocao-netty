package in.xiaocao.echo.netty.unpack.problem;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * @ClassName: EchoServer 
 * @Description: 模拟拆包粘包的服务端
 * @author zhengchong.wan
 * @date 2019年1月4日 下午7:47:15
 *
 */
public class EchoServer {
	public void run() throws Exception { 
		EventLoopGroup bossGroup = new NioEventLoopGroup(10); // 创建接收线程池
		EventLoopGroup workerGroup = new NioEventLoopGroup(20); // 创建工作线程池
		System.out.println("服务器启动成功");
		try {
			// 创建一个服务器端的程序类进行NIO启动，同时可以设置Channel
			ServerBootstrap serverBootstrap = new ServerBootstrap(); // 服务器端
			// 设置要使用的线程池以及当前的Channel类型
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
			// 接收到信息之后需要进行处理，于是定义子处理器
			serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					socketChannel.pipeline().addLast(new EchoServerHandler()); // 追加了处理器
				}
			});
			// ChannelFuture描述的时异步回调的处理操作
			ChannelFuture future = serverBootstrap.bind(9999).sync();
			future.channel().closeFuture().sync();// 等待Socket被关闭
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		new EchoServer().run();
	}
}
