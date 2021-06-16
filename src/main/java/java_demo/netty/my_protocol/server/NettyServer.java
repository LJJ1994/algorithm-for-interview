package java_demo.netty.my_protocol.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java_demo.netty.my_protocol.constants.NettyConstants;
import java_demo.netty.my_protocol.decode.NettyMessageDecoder;
import java_demo.netty.my_protocol.encode.NettyMessageEncoder;
import java_demo.netty.my_protocol.server.handler.HeartbeatRespHandler;
import java_demo.netty.my_protocol.server.handler.LoginRespAuthHandler;

import java.io.IOException;


public class NettyServer {
    NioEventLoopGroup boss = new NioEventLoopGroup();
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    public void bind() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws IOException {
                        ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                        ch.pipeline().addLast(new NettyMessageEncoder());
                        ch.pipeline().addLast("readTimeoutHandler",  new ReadTimeoutHandler(50));
                        ch.pipeline().addLast(new LoginRespAuthHandler());
                        ch.pipeline().addLast("HeartbeatHandler", new HeartbeatRespHandler());
                    }
                });

        try {
            serverBootstrap.bind(NettyConstants.HOST, NettyConstants.PORT).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("服务器启动.............");
    }

    public static void main(String[] args) {
        new NettyServer().bind();
    }
}
