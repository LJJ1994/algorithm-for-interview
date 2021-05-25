package java_demo.crypto;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class DigestDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("AaAaAa".hashCode());
        System.out.println("BBAaBB".hashCode());

        System.out.println("=========MD5==========");
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update("hello".getBytes(StandardCharsets.UTF_8));
        digest.update("world".getBytes(StandardCharsets.UTF_8));
        byte[] bytes = digest.digest();
        System.out.println(new BigInteger(1, bytes).toString(16));
        System.out.println("fc5e038d38a57032085441e7fe7010b0".length());

        System.out.println("=========SHA-1==========");
        MessageDigest instance = MessageDigest.getInstance("SHA-1");
        instance.update("hello, word".getBytes(StandardCharsets.UTF_8));
        byte[] digest1 = instance.digest();
        System.out.println(new BigInteger(1, digest1).toString(16));
        System.out.println("edb4a1195eb18dfe2e6282525c4f05bd5cc754d".length());

        System.out.println("============SHA-256============");
        MessageDigest instance1 = MessageDigest.getInstance("SHA-256");
        instance1.update("hello, world".getBytes(StandardCharsets.UTF_8));
        byte[] digest2 = instance1.digest();
        System.out.println(new BigInteger(1, digest2).toString(16));
        System.out.println("9ca7e4eaa6e8ae9c7d261167129184883644d07dfba7cbfbc4c8a2e08360d5b".length());

        Security.addProvider(new BouncyCastleProvider());
        MessageDigest ripeMD160 = MessageDigest.getInstance("RipeMD160");
        ripeMD160.update("hello world!".getBytes());
        byte[] digest3 = ripeMD160.digest();
        System.out.println(new BigInteger(1, digest3).toString(16));
    }
}
