package java_demo.crypto;

import org.bouncycastle.jcajce.provider.symmetric.CAST5;

import javax.crypto.KeyGenerator;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class SignatureDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        String msg = "hello world!";
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = generator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 用私钥对消息进行签名
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);
        signature.update(msgBytes);
        byte[] sign = signature.sign();
        System.out.println("sign msg: " + new BigInteger(1, sign).toString(16));

        // 用公钥对消息进行验证
        Signature verifySign = Signature.getInstance("SHA1withRSA");
        verifySign.initVerify(publicKey);
        verifySign.update(msgBytes);
        boolean verify = verifySign.verify(sign);
        System.out.println("verify: " + verify);
    }
}
