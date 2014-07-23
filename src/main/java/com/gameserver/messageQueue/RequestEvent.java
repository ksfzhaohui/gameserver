package com.gameserver.messageQueue;

import org.jboss.netty.channel.Channel;

import com.gameserver.net.Message;

/**
 * 请求事件
 * 
 * @author ksfzhaohui
 * 
 */
public class RequestEvent {

	/** 消息 **/
	private Message request;
	/** 消息队列 **/
	private Channel channel;

	public RequestEvent(Message request, Channel channel) {
		this.request = request;
		this.channel = channel;
	}

	public Message getRequest() {
		return request;
	}

	public void setRequest(Message request) {
		this.request = request;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

}
