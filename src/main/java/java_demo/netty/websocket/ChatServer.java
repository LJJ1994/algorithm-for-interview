package java_demo.netty.websocket;

import com.google.common.net.InetAddresses;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;
import java_demo.netty.websocket.initializer.ChatServerInitializer;

import java.net.InetSocketAddress;

public class ChatServer {
    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture start(String host, int port) {
        InetSocketAddress address = new InetSocketAddress(host, port);
        return start(address);
    }

    public ChannelFuture start(InetSocketAddress addresses) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        ChatServerInitializer initializer = new ChatServerInitializer(channelGroup);
        bootstrap.group(eventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(initializer);
        ChannelFuture channelFuture = bootstrap.bind(addresses);
        channelFuture.syncUninterruptibly();
        channel = channelFuture.channel();
        return channelFuture;
    }

    public void destroy() {
        if (channel != null) {
            channel.close();
        }
        channelGroup.close();
        eventLoopGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 1234;
        ChatServer chatServer = new ChatServer();
        System.out.println("服务器启动................");
        ChannelFuture channelFuture = chatServer.start(host, port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                chatServer.destroy();
            }
        });
        channelFuture.channel().closeFuture().syncUninterruptibly();
    }
}
