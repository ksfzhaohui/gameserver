package com.gameserver.net.codec;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import com.gameserver.net.Message;

/**
 * 解码器
 *
 * 将二进制数据转换成需要的业务逻辑对象
 * @author zhaohui
 *
 */
public abstract class Decoder extends OneToOneDecoder {

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if (!(msg instanceof Message)) {
			return msg;
		}
		Message message = (Message) msg;
		Object logicObj = transformData(message.getData());
		message.setData(logicObj);
		return message;
	}

	/**
	 * 将二进制数据转换成逻辑对象
	 * 
	 * @param msg   
	 * 					请求对象
	 * @throws Exception
	 */
	protected abstract Object transformData(Object msg) throws Exception;

}
