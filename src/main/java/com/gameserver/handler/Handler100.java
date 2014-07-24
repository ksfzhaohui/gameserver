package com.gameserver.handler;

import com.gameserver.AbstractGameHandler;
import com.gameserver.net.Message;
import com.gameserver.util.GsException;

public class Handler100 extends AbstractGameHandler {

	@Override
	public void execute(Message request, Message response) throws GsException {
		System.out.println(request.getData());
		response.setData(request.getData());
	}

}
