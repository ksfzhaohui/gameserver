package com.gameserver;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.gameserver.config.ServerConfig;
import com.gameserver.util.SpringContainer;

/**
 * 网关服启动程序
 * 
 * @author Administrator
 * 
 */
public class ServerMain {

	private static final Logger logger = Logger.getLogger(ServerMain.class);

	public static void main(String[] args) {
		ServerConfig.load();

		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory());
		final ServerHandler handler = new ServerHandler();
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("handler", handler);
				return pipeline;
			}
		});
		int port = ServerConfig.getPort();
		bootstrap.bind(new InetSocketAddress(port));

		SpringContainer.getInstance().loadSpring();

		logger.info("============Server Startup OK port on " + port
				+ "==============");
	}

}
