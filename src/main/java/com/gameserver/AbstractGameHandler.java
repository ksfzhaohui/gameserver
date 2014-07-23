package com.gameserver;

import com.gameserver.net.Message;
import com.gameserver.util.GsException;

/**
 * 业务处理类基类
 * 
 * @author zhaohui
 *
 */
public abstract class AbstractGameHandler {

	/**
	 * 业务处理方法
	 * 
	 * @param request
	 *        客户端请求
	 * @param response
	 *        服务器响应
	 */
	abstract public void execute(Message request, Message response)
			throws GsException;

}
