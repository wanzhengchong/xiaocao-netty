package in.xiaocao.echo.netty.decoder.messagepack;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessagePackEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		MessagePack pack = new MessagePack();
		pack.write(msg);
		byte[] data = pack.write(msg);
		out.writeBytes(data);
	}

}
