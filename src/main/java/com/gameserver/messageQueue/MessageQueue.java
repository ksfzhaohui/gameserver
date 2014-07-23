package com.gameserver.messageQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.Channel;

import com.gameserver.ServerHandler;
import com.gameserver.net.Message;

/**
 * 玩家请求队列
 * 
 * @author zhaohui
 * 
 */
public class MessageQueue implements Runnable {

	private static Logger logger = Logger.getLogger(MessageQueue.class);
	/** 处理线程 **/
	private ExecutorService executor;
	/** 消息阻塞队列 **/
	private BlockingQueue<RequestEvent> linkedQueue = new LinkedBlockingQueue<RequestEvent>();

	private ServerHandler serverHandler;

	public MessageQueue(ServerHandler serverHandler) {
		executor = Executors.newSingleThreadExecutor();
		this.serverHandler = serverHandler;
	}

	@Override
	public void run() {
		try {
			while (true) {
				RequestEvent requestEvent = linkedQueue.take();
				if (requestEvent != null) {
					Message request = requestEvent.getRequest();
					Channel channel = requestEvent.getChannel();

					channel.write(serverHandler.processRequest(request));
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * 添加请求消息
	 * 
	 * @param requestEvent
	 */
	public void put(RequestEvent requestEvent) {
		linkedQueue.offer(requestEvent);
	}

	/**
	 * 启动线程
	 */
	public void start() {
		executor.execute(this);
	}

	/**
	 * 中断启动的线程
	 */
	public void showdown() {
		executor.shutdown();
	}

}
