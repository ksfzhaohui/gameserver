package com.gameserver.net;

/**
 * 消息
 * 
 * @author zhaohui
 * 
 */
public class Message {
	/** 头消息 **/
	private Header header;
	/** 数据 **/
	private Object data;

	public Message() {

	}

	public Message(Header header) {
		this.header = header;
	}

	public Message(Header header, Object data) {
		this.header = header;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

}
