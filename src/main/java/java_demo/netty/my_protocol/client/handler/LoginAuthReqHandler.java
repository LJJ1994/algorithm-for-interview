package java_demo.netty.my_protocol.client.handler;

import io.netty.channel.*;
import java_demo.netty.my_protocol.message.Header;
import java_demo.netty.my_protocol.message.NettyMessage;
import java_demo.netty.my_protocol.type.MessageType;

public class LoginAuthReqHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.fireExceptionCaught(cause);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(builderLoginReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = ((NettyMessage) msg);
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.HANDSHAKE_RESP.value()) {
            byte result = ((byte) message.getBody());
            if (result != MessageType.HANDSHAKE_RESP.value()) {
                ctx.close();
            } else {
                System.out.println("登录成功: " + message);
                ctx.fireChannelRead(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
        }

    }

    private NettyMessage builderLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HANDSHAKE_REQ.value());
        message.setHeader(header);
        return message;
    }
}
