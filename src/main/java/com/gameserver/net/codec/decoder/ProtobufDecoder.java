package com.gameserver.net.codec.decoder;

import com.gameserver.net.Message;
import com.gameserver.net.codec.Decoder;

/**
 * pb格式数据转换成业务逻辑对象
 * @author zhaohui
 *
 */
public class ProtobufDecoder extends Decoder {

	//	@Override
	//	protected void transformData(Object logicObj, Request request)
	//			throws Exception {
	//		if (logicObj instanceof MessageLite) {
	//			MessageLite messageLite = (MessageLite) logicObj;
	//			MessageLite prototype = messageLite.getDefaultInstanceForType();
	//			messageLite = prototype.newBuilderForType()
	//					.mergeFrom((byte[]) request.getData()).build();
	//			request.setData(messageLite);
	//		} else {
	//			throw new GsException(ErrorCode.CMD_OBJ_TYPE_ERROR);
	//		}
	//	}

	@Override
	protected void transformData(int commandId, Message message)
			throws Exception {

	}
}
