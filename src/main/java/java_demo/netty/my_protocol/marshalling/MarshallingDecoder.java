package java_demo.netty.my_protocol.marshalling;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MarshallingDecoder {
    private Unmarshaller unmarshaller;

    public MarshallingDecoder() throws IOException {
        this.unmarshaller = MarshallingCodecFactory.buildUnMarshalling();
    }

    public Object decode(ByteBuf in) {
        // 获取当前readerIndex的32位值，readerIndex索引 + 4
        // 当前位置的值就是对象的长度
        int objSize = in.readInt();
        // 获取存储对象的ByteBuf
        ByteBuf buf = in.slice(in.readerIndex(), objSize);
        ChannelBufferByteInput input = new ChannelBufferByteInput(buf);
        try {
            unmarshaller.start(input);
            Object object = unmarshaller.readObject();
            unmarshaller.finish();
            // readerIndex 移动对象长度个单位
            in.readerIndex(in.readerIndex() + objSize);
            return object;
        } catch (Exception e) {
            throw new RuntimeException("decode ByteBuf error, " + e.getMessage());
        }
    }

    public static void main(String[] args) {
//        ByteBuf byteBuf = Unpooled.copiedBuffer("123456".getBytes(StandardCharsets.UTF_8));
//        System.out.println((int)byteBuf.readByte());
//        System.out.println(byteBuf.readerIndex());
//
//        System.out.println((int)byteBuf.readByte());
//        System.out.println(byteBuf.readerIndex());
//
//        System.out.println((int)byteBuf.readByte());
//        System.out.println(byteBuf.readerIndex());


    }
}
