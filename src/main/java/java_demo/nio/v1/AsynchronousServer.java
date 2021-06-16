package java_demo.nio.v1;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.Channels;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsynchronousServer {
    public static void start() throws IOException, ExecutionException, InterruptedException, ClassNotFoundException {
        AsynchronousServerSocketChannel socketChannel = AsynchronousServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress("127.0.0.1", 2001));
        Future<AsynchronousSocketChannel> channelFuture = socketChannel.accept();
        System.out.println("等待客户端的连接............");
        channelFuture.get();

        AsynchronousSocketChannel clientSocket = channelFuture.get();
        if (clientSocket != null && clientSocket.isOpen()) {
            InputStream clientInputStream = Channels.newInputStream(clientSocket);
            ObjectInputStream ois = new ObjectInputStream(clientInputStream);
            while (true) {
                Object object = ois.readObject();
                if ("EOF".equals(object)) {
                    clientSocket.close();
                    break;
                }
                System.out.println("接收客户端发来的消息: " + object);
            }
            ois.close();
            clientInputStream.close();
        }

    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, ClassNotFoundException {
        AsynchronousServer.start();
    }
}
