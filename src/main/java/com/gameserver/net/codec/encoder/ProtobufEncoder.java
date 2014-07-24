package com.gameserver.net.codec.encoder;

import static org.jboss.netty.buffer.ChannelBuffers.wrappedBuffer;

import com.gameserver.net.codec.Encoder;
import com.google.protobuf.MessageLite;

/**
 *  业务逻辑对象转换成二进制数据
 * @author zhaohui
 *
 */
public class ProtobufEncoder extends Encoder {

	@Override
	protected Object transformData(Object msg) throws Exception {
		if (msg instanceof MessageLite) {
			return wrappedBuffer(((MessageLite) msg).toByteArray());
		}
		if (msg instanceof MessageLite.Builder) {
			return wrappedBuffer(((MessageLite.Builder) msg).build()
					.toByteArray());
		}
		return msg;
	}
}
