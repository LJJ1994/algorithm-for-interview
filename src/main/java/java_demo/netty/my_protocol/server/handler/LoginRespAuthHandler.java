package java_demo.netty.my_protocol.server.handler;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java_demo.netty.my_protocol.message.Header;
import java_demo.netty.my_protocol.message.NettyMessage;
import java_demo.netty.my_protocol.type.MessageType;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LoginRespAuthHandler extends ChannelHandlerAdapter {
    // 节点检查，是否已登录
    private static final ConcurrentMap<String, Boolean> nodeCheck = new ConcurrentHashMap<>();
    // 白名单机制
    private static final List<String> ipWhiteList = new ArrayList<>();

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        nodeCheck.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        // 如果是握手请求
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.HANDSHAKE_REQ.value()) {
            NettyMessage loginResp = null;
            // 1.判断是否重复登录
            String nodeIndex = ctx.channel().remoteAddress().toString();
            if (nodeCheck.containsKey(nodeIndex)) {
                loginResp = buildResp(MessageType.HANDSHAKE_FAIL.value());
            } else {// 2.判断是否在白名单
                InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
                String hostName = socketAddress.getHostName();
                boolean isOk = false;
                for (String s : ipWhiteList) {
                    if (s.equals(hostName)) {
                        isOk = true;
                        break;
                    }
                }
                loginResp = isOk ? buildResp(MessageType.HANDSHAKE_RESP.value()) : buildResp(MessageType.HANDSHAKE_FAIL.value());
                if (isOk) {
                    nodeCheck.put(nodeIndex, Boolean.TRUE);
                }
            }
            System.out.println("登录响应：" + loginResp + "响应体：" + loginResp.getBody());
            ctx.writeAndFlush(loginResp);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildResp(byte result) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HANDSHAKE_RESP.value());
        message.setHeader(header);
        message.setBody(result);
        return message;
    }
}
