package com.gameserver;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.gameserver.datalayer.protocol.Protocol;
import com.gameserver.datalayer.protocol.Protocol.Login;
import com.gameserver.datalayer.protocol.Protocol.Request;
import com.gameserver.net.Header;
import com.gameserver.net.Message;

public class ClientHandler extends SimpleChannelHandler {

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		Login login = Login.newBuilder().setUser("ksfzhaohui")
				.setPswd("11111111").build();
		Request.Builder builder = Request.newBuilder();
		builder.setId(100);
		builder.setExtension(Protocol.login, login);
		Request request = builder.build();

		Header header = new Header("11111111111111111111111111111111");
		header.setCommandId(100);
		Message message = new Message(header, request);
		e.getChannel().write(message);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Message message = (Message) e.getMessage();
		System.out.println("cmd:" + message.getHeader().getCommandId());
		System.out.println(message.getData());
	}
}
