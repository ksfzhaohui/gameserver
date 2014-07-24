package com.gameserver;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.gameserver.datalayer.protocol.Protocol;
import com.gameserver.net.codec.HeaderDecoder;
import com.gameserver.net.codec.HeaderEncoder;
import com.gameserver.net.codec.decoder.ProtobufDecoder;
import com.gameserver.net.codec.encoder.ProtobufEncoder;
import com.google.protobuf.ExtensionRegistry;

public class Client {

	public static void main(String[] args) {
		ClientBootstrap cbApp = new ClientBootstrap(
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		final ClientHandler handler = new ClientHandler();
		final ExtensionRegistry registry = ExtensionRegistry.newInstance();
		Protocol.registerAllExtensions(registry);

		cbApp.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new HeaderDecoder());
				pipeline.addLast("pDecoder", new ProtobufDecoder(
						Protocol.Request.getDefaultInstance(), registry));

				pipeline.addLast("hEncoder", new HeaderEncoder());
				pipeline.addLast("pEncoder", new ProtobufEncoder());
				pipeline.addLast("handler", handler);
				return pipeline;
			}
		});
		ChannelFuture future = cbApp.connect(new InetSocketAddress("localhost",
				8081));
		future.getChannel().getCloseFuture().awaitUninterruptibly();
		cbApp.releaseExternalResources();
	}

}
