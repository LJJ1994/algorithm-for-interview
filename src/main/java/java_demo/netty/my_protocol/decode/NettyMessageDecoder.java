package java_demo.netty.my_protocol.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import java_demo.netty.my_protocol.marshalling.MarshallingCodecFactory;
import java_demo.netty.my_protocol.marshalling.MarshallingDecoder;
import java_demo.netty.my_protocol.message.Header;
import java_demo.netty.my_protocol.message.NettyMessage;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {
    private MarshallingDecoder marshallingDecoder;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) throws IOException {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        this.marshallingDecoder = new MarshallingDecoder();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf buf = ((ByteBuf) super.decode(ctx, in));
        if (buf == null) {
            return null;
        }
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        header.setSessionId(in.readLong());
        header.setType(in.readByte());
        header.setPriority(in.readByte());

        // attachment长度
        int size = in.readInt();
        if(size > 0) {
            Map<String, Object> attach = new HashMap<String, Object>(size);
            int keySize = 0;
            byte[] keyArr = null;
            String key = null;
            for (int i = 0; i < size; i++) {
               keySize = in.readInt();
               keyArr = new byte[keySize];
               in.readBytes(keyArr);
               key = new String(keyArr, StandardCharsets.UTF_8);
               attach.put(key, marshallingDecoder.decode(in));
            }
            key = null;
            keyArr = null;
            header.setAttachment(attach);
        }
        if (in.readableBytes() > 4) {
            message.setBody(marshallingDecoder.decode(in));
        }

        message.setBody(header);
        return message;
    }
}
