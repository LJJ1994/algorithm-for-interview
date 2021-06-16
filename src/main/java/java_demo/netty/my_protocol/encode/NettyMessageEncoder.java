package java_demo.netty.my_protocol.encode;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java_demo.netty.my_protocol.marshalling.MarshallingEncoder;
import java_demo.netty.my_protocol.message.Header;
import java_demo.netty.my_protocol.message.NettyMessage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {
    private MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() throws IOException {
        this.marshallingEncoder = new MarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
        if (msg == null || msg.getHeader() == null) {
            throw new RuntimeException("The decode msg is null.");
        }
        // ByteBuf 值排序为(括号为字节数)：
        // crcCode(4) + length(4) + sessionId(8) + type(1) + priority(1) + attachment长度(4) + 【attachment的值】 + 【消息体的值(如果有)】
        // 1) 【attachment的值】: 键的长度 + 键的字节数组表示 + （键对应的值的长度 + 键对应的值） (后面两个在encode方法里面处理)
        //  key(4) + keyArr(不限) + valueLength(4) + value(不限)
        // 2) 【消息体的长度】
        //  如果有：(在encode方法里面)
        //       bodyLength(4) + body(不限)
        //  没有：
        //      直接想写0

        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(msg.getHeader().getCrcCode());
        byteBuf.writeInt(msg.getHeader().getLength());
        byteBuf.writeLong(msg.getHeader().getSessionId());
        byteBuf.writeByte(msg.getHeader().getType());
        byteBuf.writeByte(msg.getHeader().getPriority());

        // 1.先写附加信息的长度
        byteBuf.writeInt(msg.getHeader().getAttachment().size());
        String key = null;
        byte[] keyValue = null;
        Object value = null;
        // 2.循环写入附加信息的值
        for (Map.Entry<String, Object> entry : msg.getHeader().getAttachment().entrySet()) {
            key = entry.getKey();
            keyValue = key.getBytes(StandardCharsets.UTF_8);
            // 2.1 先写键的长度
            byteBuf.writeInt(keyValue.length);
            // 2.2 再写键的值（字节数组表示）
            byteBuf.writeBytes(keyValue);
            // 2.3 最后用marshall序列化，写value
            value = entry.getValue();
            marshallingEncoder.encode(value, byteBuf);
        }
        // gc
        key = null;
        keyValue = null;
        value = null;

        if (msg.getBody() != null) {
            marshallingEncoder.encode(msg.getBody(), byteBuf);
        } else {
            byteBuf.writeInt(0);
        }

        // 设置消息长度字段为可读字节数
        byteBuf.setInt(4, byteBuf.readableBytes());
    }
}
