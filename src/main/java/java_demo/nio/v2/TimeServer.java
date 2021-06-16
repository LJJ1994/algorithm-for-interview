package java_demo.nio.v2;

public class TimeServer {
    public static void main(String[] args) {
        int port = 8001;
        AsyncTimeServerHandler serverHandler = new AsyncTimeServerHandler(port);
        new Thread(serverHandler).start();
    }
}
