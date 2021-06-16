package java_demo.crypto;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HmacDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        KeyGenerator generator = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = generator.generateKey();
        byte[] encoded = secretKey.getEncoded();
        System.out.println(new BigInteger(1, encoded).toString(16));
        System.out.println(Arrays.toString(encoded));

        Mac hmacMd5 = Mac.getInstance("HmacMd5");
        hmacMd5.init(secretKey);
        hmacMd5.update("hello world!".getBytes(StandardCharsets.UTF_8));
        byte[] bytes = hmacMd5.doFinal();
        System.out.println(new BigInteger(1, bytes).toString(16));

        System.out.println("restore from hkey");
        final byte[] hkey = {116, 126, 108, 2, -35, -54, -97, -64, 98, -47, 64, -123, 101, -48, -64, 39, -47, 55, -64, 69, 114, -54, 34, 87, -127, 104, -67, -81, -121, -5, 51, 107, 55, 12, -2, -33, 65, 21, -95, 8, 11, -31, -84, -116, 121, 33, -34, 68, -48, -31, -103, 94, -120, 93, -53, -56, -63, -95, 55, 124, 59, -123, -71, 118};

        SecretKeySpec keySpec = new SecretKeySpec(hkey, "HmacMD5");
        Mac hmacMD5 = Mac.getInstance("HmacMD5");
        hmacMD5.init(keySpec);
        hmacMD5.update("hello world!".getBytes(StandardCharsets.UTF_8));
        byte[] bytes1 = hmacMD5.doFinal();
        System.out.println(Arrays.toString(bytes1));
        System.out.println(new BigInteger(1, bytes1).toString(16));
    }
}
