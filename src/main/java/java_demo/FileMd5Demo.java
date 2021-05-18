package java_demo;

import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class FileMd5Demo {
    public static void main(String[] args) throws IOException {
        generate();
        ClassLoader classLoader = FileMd5Demo.class.getClassLoader();
        InputStream stream = classLoader.getResourceAsStream("test.txt");
        String s = null;
        try {
            if (stream == null) {
                throw new RuntimeException("file not exit!");
            }
            s = DigestUtils.md5Hex(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }

    // generate 1G file
    private static void generate() throws IOException {
        String path = "test.txt";
        FileOutputStream stream = new FileOutputStream(path);
        OutputStreamWriter writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
        // 32 * 1000000 = 32 000 000
        for (int i = 0; i < 1000000; i++) {
            writer.write(i);
        }
        writer.close();
        stream.close();
    }
}
