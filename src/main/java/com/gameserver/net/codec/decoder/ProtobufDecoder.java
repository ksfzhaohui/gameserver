package com.gameserver.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;

import com.gameserver.net.codec.Decoder;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.MessageLite;

/**
 * pb格式数据转换成业务逻辑对象
 * @author zhaohui
 *
 */
public class ProtobufDecoder extends Decoder {

	private final MessageLite prototype;
	private final ExtensionRegistry extensionRegistry;

	public ProtobufDecoder(MessageLite prototype) {
		this(prototype, null);
	}

	public ProtobufDecoder(MessageLite prototype,
			ExtensionRegistry extensionRegistry) {
		if (prototype == null) {
			throw new NullPointerException("prototype");
		}
		this.prototype = prototype.getDefaultInstanceForType();
		this.extensionRegistry = extensionRegistry;
	}

	@Override
	protected Object transformData(Object msg) throws Exception {
		if (!(msg instanceof ChannelBuffer)) {
			return msg;
		}

		ChannelBuffer buf = (ChannelBuffer) msg;
		if (buf.hasArray()) {
			final int offset = buf.readerIndex();
			if (extensionRegistry == null) {
				return prototype
						.newBuilderForType()
						.mergeFrom(buf.array(), buf.arrayOffset() + offset,
								buf.readableBytes()).build();
			} else {
				return prototype
						.newBuilderForType()
						.mergeFrom(buf.array(), buf.arrayOffset() + offset,
								buf.readableBytes(), extensionRegistry).build();
			}
		} else {
			if (extensionRegistry == null) {
				return prototype
						.newBuilderForType()
						.mergeFrom(
								new ChannelBufferInputStream(
										(ChannelBuffer) msg)).build();
			} else {
				return prototype
						.newBuilderForType()
						.mergeFrom(
								new ChannelBufferInputStream(
										(ChannelBuffer) msg), extensionRegistry)
						.build();
			}
		}
	}
}
