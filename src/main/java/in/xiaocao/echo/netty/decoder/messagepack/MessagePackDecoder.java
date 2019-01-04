package in.xiaocao.echo.netty.decoder.messagepack;

import java.util.List;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class MessagePackDecoder extends MessageToMessageDecoder<ByteBuf> {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		int len = msg.readableBytes();
		byte[] data = new byte[len];
		msg.getBytes(msg.readerIndex(), data, 0, len);
		
		MessagePack pack = new MessagePack();
		out.add(pack.read(data, pack.lookup(Cat.class)));
	}



}
