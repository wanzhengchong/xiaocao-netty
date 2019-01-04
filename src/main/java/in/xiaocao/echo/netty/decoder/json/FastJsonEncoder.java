package in.xiaocao.echo.netty.decoder.json;

import com.alibaba.fastjson.JSONObject;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class FastJsonEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		String json = JSONObject.toJSONString(msg);
		byte[] data = json.getBytes();
		out.writeBytes(data);
	}

}
