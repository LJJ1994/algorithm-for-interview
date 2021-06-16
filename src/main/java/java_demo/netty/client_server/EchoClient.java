package java_demo.netty.client_server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {
    private int port;
    private String host;

    public EchoClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    private void start() throws InterruptedException {
        EchoClientHandler echoClientHandler = new EchoClientHandler();
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class)
                    .group(eventLoopGroup)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(echoClientHandler);
                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();
//            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EchoClient echoClient = new EchoClient(1200, "127.0.0.1");
        System.out.println("客户端启动........");
        echoClient.start();
    }
}
