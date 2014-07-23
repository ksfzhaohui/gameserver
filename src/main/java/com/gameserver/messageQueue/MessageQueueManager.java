package com.gameserver.messageQueue;

import java.util.Map;

import org.jboss.netty.util.internal.ConcurrentHashMap;

import com.gameserver.ServerHandler;

/**
 * 玩家处理队列管理器
 * 
 * @author zhaohui
 * 
 */
public class MessageQueueManager {

	private Map<String, MessageQueue> playerMessageQueueMap = new ConcurrentHashMap<String, MessageQueue>();

	private ServerHandler serverHandler;

	public MessageQueueManager(ServerHandler serverHandler) {
		this.serverHandler = serverHandler;
	}

	/**
	 * 往队列中添加请求消息
	 * 
	 * @param request
	 */
	public void addRequest(RequestEvent requestEvent) {
		String sessionId = requestEvent.getRequest().getHeader().getSessionid();
		synchronized (this) {
			MessageQueue processQueue = playerMessageQueueMap.get(sessionId);
			if (processQueue == null) {
				processQueue = new MessageQueue(serverHandler);
				playerMessageQueueMap.put(sessionId, processQueue);
				processQueue.start();
			}
			processQueue.put(requestEvent);
		}
	}

	/**
	 * 玩家掉线移除玩家处理队列
	 * 
	 * @param sessionId
	 *            玩家session
	 */
	public void removeProcessQueue(String sessionId) {
		MessageQueue messageQueue = playerMessageQueueMap.remove(sessionId);
		if (messageQueue != null) {
			messageQueue.showdown();
		}
	}
}
