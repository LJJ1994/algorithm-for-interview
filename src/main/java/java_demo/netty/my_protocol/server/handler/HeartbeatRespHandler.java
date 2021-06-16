package java_demo.netty.my_protocol.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java_demo.netty.my_protocol.message.Header;
import java_demo.netty.my_protocol.message.NettyMessage;
import java_demo.netty.my_protocol.type.MessageType;

public class HeartbeatRespHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage nettyMessage = (NettyMessage) msg;
        if (nettyMessage.getHeader() != null && nettyMessage.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            System.out.println("收到客户端的心跳检测：" + nettyMessage);
            NettyMessage message = new NettyMessage();
            Header header = new Header();
            header.setType(MessageType.HEARTBEAT_RESP.value());
            message.setHeader(header);
            System.out.println("发送心跳应答给客户端：" + message);
            ctx.writeAndFlush(message);
        } else {
            ctx.fireChannelRead(msg);
        }
    }
}
