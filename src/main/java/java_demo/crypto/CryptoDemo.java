package java_demo.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class CryptoDemo {
    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String msg = "hello world";
        System.out.println("message: " + msg);
        byte[] key = "1234567890abcdef1234567890abcdef".getBytes(StandardCharsets.UTF_8);
        byte[] data = msg.getBytes(StandardCharsets.UTF_8);

        // 加密
        byte[] encrypt = encrypt(data, key);
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypt));

        // 解密
        byte[] decrypt = decrypt(encrypt, key);
        System.out.println("Decrypted: " + new String(decrypt, StandardCharsets.UTF_8));
    }

    public static byte[] encrypt(byte[] input, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] iv = random.generateSeed(16);
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivps);
        byte[] data = cipher.doFinal(input);
        return join(iv, data);
    }

    public static byte[] decrypt(byte[] input, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] iv = new byte[16];
        byte[] data = new byte[input.length - 16];
        System.arraycopy(input, 0, iv, 0, iv.length);
        System.arraycopy(input, 16, data, 0, data.length);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivps = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivps);
        return cipher.doFinal(data);
    }

    private static byte[] join(byte[] b1, byte[] b2) {
        byte[] ret = new byte[b1.length + b2.length];
        System.arraycopy(b1, 0, ret, 0, b1.length);
        System.arraycopy(b2, 0, ret, b1.length, b2.length);
        return ret;
    }
}
