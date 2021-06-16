package java_demo.nio.v2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncTimeServerHandler implements Runnable{
    private CountDownLatch countDownLatch = null;
    private AsynchronousServerSocketChannel serverSocketChannel = null;
    private AsynchronousChannelGroup asynchronousChannelGroup = null;
    public AsyncTimeServerHandler(int port) {
        try {
            asynchronousChannelGroup = AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 10);
            serverSocketChannel = AsynchronousServerSocketChannel.open(asynchronousChannelGroup);
            serverSocketChannel.bind(new InetSocketAddress(port));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("服务器启动，端口为：" + port);
    }

    @Override
    public void run() {
        countDownLatch = new CountDownLatch(1);
        doAccept();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept() {
        serverSocketChannel.accept(this, new CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler>() {
            @Override
            public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
                // 继续接收处理客户端连接.....
                attachment.serverSocketChannel.accept(attachment, this);
                ByteBuffer buf = ByteBuffer.allocate(1024);
                result.read(buf, buf, new ReadCompleteHandler(result));
            }

            @Override
            public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
                try {
                    exc.printStackTrace();
                    getCountDownLatch().countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    // 服务器端读完成处理器
    private static class ReadCompleteHandler implements CompletionHandler<Integer, ByteBuffer> {
        private AsynchronousSocketChannel channel;

        public ReadCompleteHandler(AsynchronousSocketChannel channel) {
            this.channel = channel;
        }

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            attachment.flip();
            byte[] body = new byte[attachment.remaining()];
            attachment.get(body);

            String s = new String(body, StandardCharsets.UTF_8);
            System.out.println("接收到客户端发来的数据: " + s);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(s) ? new java.util.Date(
                    System.currentTimeMillis()).toString() : "BAD ORDER";
            //调用doWrite方法发送给客户端。
            doWrite(currentTime);
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            try {
                channel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void doWrite(String currentTime) {
            if (currentTime != null && currentTime.trim().length() > 0) {
                //首先对当前时间进行合法性校验，如果合法，调用字符串的解码方法将应答消息编码成字节数组，
                //然后将它复制到发送缓冲区writeBuffer中，
                byte[] bytes = (currentTime).getBytes();
                ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
                writeBuffer.put(bytes);
                writeBuffer.flip();
                //最后调用AsynchronousSocketChannel的异步write方法。
                //正如前面介绍的异步read方法一样，它也有三个与read方法相同的参数，
                //在本例程中我们直接实现write方法的异步回调接口CompletionHandler。
                channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer buffer) {
                        //对发送的writeBuffer进行判断，如果还有剩余的字节可写，说明没有发送完成，需要继续发送，直到发送成功。
                        if (buffer.hasRemaining())
                            channel.write(buffer, buffer, this);
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        try {
                            channel.close();
                        } catch (IOException e) {

                        }
                    }
                });
            }
        }
    }
}
