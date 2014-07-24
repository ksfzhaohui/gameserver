package com.gameserver.net.codec.decoder;

import com.gameserver.net.Message;
import com.gameserver.net.codec.Decoder;

/**
 * pb格式数据转换成业务逻辑对象
 * @author zhaohui
 *
 */
public class ProtobufDecoder extends Decoder {

	@Override
	protected void transformData(int commandId, Message message)
			throws Exception {

	}
}
