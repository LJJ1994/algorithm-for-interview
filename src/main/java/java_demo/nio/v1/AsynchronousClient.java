package java_demo.nio.v1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.Channels;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsynchronousClient {
    public static void start() throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        System.out.println("连接服务器........");
        Future<Void> future = socketChannel.connect(new InetSocketAddress("127.0.0.1", 2001));
        future.get();
        System.out.println("连接服务器完成....");

        OutputStream outputStream = Channels.newOutputStream(socketChannel);
        ObjectOutputStream ois = new ObjectOutputStream(outputStream);

        System.out.println("开始发送信息..........");
        for (int i = 0; i < 5; i++) {
            ois.writeObject("yes, this is for you [" + i + "]");
        }
        ois.writeObject("EOF");
        ois.close();
        outputStream.close();
        System.out.println("发送完成");
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousClient.start();
    }
}
