package java_demo.netty.client_server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


import java.net.InetSocketAddress;

public class EchoServer {
    private int port;
    public EchoServer(int port) {
        this.port = port;
    }

    private void start() throws InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EchoServerHandler echoServerHandler = new EchoServerHandler();
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(echoServerHandler);
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EchoServer echoServer = new EchoServer(1200);
        System.out.println("服务器启动..........");
        echoServer.start();
        System.out.println("服务器启动完成.............");

    }
}
