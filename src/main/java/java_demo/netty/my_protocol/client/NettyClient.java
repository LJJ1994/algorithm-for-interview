package java_demo.netty.my_protocol.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java_demo.netty.my_protocol.client.handler.HeartbeatReqHandler;
import java_demo.netty.my_protocol.client.handler.LoginAuthReqHandler;
import java_demo.netty.my_protocol.constants.NettyConstants;
import java_demo.netty.my_protocol.decode.NettyMessageDecoder;
import java_demo.netty.my_protocol.encode.NettyMessageEncoder;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NettyClient {
    private int port;
    private String host;

    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public NettyClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public NettyClient() {}

    public void start(int port, String host) throws InterruptedException {
        try {
            NioEventLoopGroup loopGroup = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                            ch.pipeline().addLast("MessageEncoder", new NettyMessageEncoder());
                            ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                            ch.pipeline().addLast("LoginAuthHandler", new LoginAuthReqHandler());
                            ch.pipeline().addLast("HeartbeatHandler", new HeartbeatReqHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(host, port),
                    new InetSocketAddress(NettyConstants.LOCAL_HOST, NettyConstants.LOCAL_PORT)).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        try {
                            start(NettyConstants.PORT, NettyConstants.HOST);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NettyClient client = new NettyClient();
        System.out.println(client.format(new Date()) + " 客户端启动..............");
        client.start(NettyConstants.PORT, NettyConstants.HOST);
    }

    public String format(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
