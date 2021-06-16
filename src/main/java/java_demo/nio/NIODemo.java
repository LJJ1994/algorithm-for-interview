package java_demo.nio;

import com.sun.beans.editors.ByteEditor;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIODemo {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("test.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer buf = ByteBuffer.allocate(1024 * 20);
        buf.clear();
        channel.read(buf, 0, buf , new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                // 切换读模式
                attachment.flip();
                System.out.println("result: " + result);
                System.out.println("attachment: " + attachment.toString());

                byte[] bytes = new byte[attachment.limit()];
                attachment.get(bytes);
                System.out.println("received data : " + new String(bytes));
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                throw new RuntimeException("some thing has error: " + exc.getMessage());
            }
        });
    }

    private static class MsgHandler {
    }
}
