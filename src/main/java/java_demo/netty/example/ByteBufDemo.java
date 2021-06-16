package java_demo.netty.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class ByteBufDemo {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.copiedBuffer("hello world!", StandardCharsets.UTF_8);
        System.out.println(buffer.refCnt());
        buffer.release();
        System.out.println(buffer.getByte(0));
    }
}
