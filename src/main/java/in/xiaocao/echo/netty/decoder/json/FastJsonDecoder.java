package in.xiaocao.echo.netty.decoder.json;

import java.util.List;

import com.alibaba.fastjson.JSON;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class FastJsonDecoder extends MessageToMessageDecoder<ByteBuf> {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		int len = msg.readableBytes();
		byte[] data = new byte[len];
		msg.getBytes(msg.readerIndex(), data, 0, len);
		out.add(JSON.parseObject(new String(data), Cat.class));
	}

}
