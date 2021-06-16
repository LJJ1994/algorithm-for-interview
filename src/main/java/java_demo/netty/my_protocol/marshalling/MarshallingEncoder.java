package java_demo.netty.my_protocol.marshalling;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.Marshaller;

import java.io.IOException;

public class MarshallingEncoder {
    private Marshaller marshaller;
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    public MarshallingEncoder() throws IOException {
        this.marshaller = MarshallingCodecFactory.buildMarshalling();
    }

    public  void encode(Object msg, ByteBuf out) {
        try {
            int lengthPos = out.writerIndex();
            out.writeBytes(LENGTH_PLACEHOLDER);

            ChannelBufferByteOutput output = new ChannelBufferByteOutput(out);
            marshaller.start(output);
            marshaller.writeObject(msg);
            marshaller.finish();
            // 设置该value的字节长度（key -> value）或者消息体的长度
            out.setInt(lengthPos, out.writerIndex() - lengthPos - 4);
        } catch (Exception e) {
            throw new RuntimeException("encode msg error, " + e.getMessage());
        } finally {
            if (marshaller != null) {
                try {
                    marshaller.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
