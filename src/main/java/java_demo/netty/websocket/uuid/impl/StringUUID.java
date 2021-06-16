package java_demo.netty.websocket.uuid.impl;

import java_demo.netty.websocket.uuid.UUID;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

public class StringUUID implements UUID {
    @Override
    public int generate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(100);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String uid = "255.255.255.255";
        long len = 15 * 1000000; // 15 000 000 = 15M
        System.out.println(uid.getBytes("utf8").length);
        System.out.println(len / (1024 * 1024));
    }
}
