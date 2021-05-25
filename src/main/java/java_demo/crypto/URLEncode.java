package java_demo.crypto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class URLEncode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String url = "https://www.baidu.com/?q=中文";
        String encode = URLEncoder.encode(url, "UTF-8");
        System.out.println(encode); // https%3A%2F%2Fwww.baidu.com%2F%3Fq%3D%E4%B8%AD%E6%96%87
        String decode = URLDecoder.decode("https%3A%2F%2Fwww.baidu.com%2F%3Fq%3D%E4%B8%AD%E6%96%87", String.valueOf(StandardCharsets.UTF_8));
        System.out.println(decode);

        byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad };
        String s = Base64.getEncoder().encodeToString(input);
        System.out.println(s);
        byte[] decode1 = Base64.getDecoder().decode(s);
        System.out.println(Arrays.toString(decode1));

        String email = "optionsci111@gmail.com";
        byte[] encode1 = Base64.getEncoder().encode(email.getBytes(StandardCharsets.UTF_8));
        System.out.println(new String(encode1)); // b3B0aW9uc2NpMTExQGdtYWlsLmNvbQ==
        byte[] decode2 = Base64.getDecoder().decode(encode1);
        System.out.println(new String(decode2));

    }
}
